package funmarket.image;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ImageRestController {
    @Autowired
    GridfsStore gridfsStore;

    @RequestMapping(value="/images", method=RequestMethod.POST)
    public @ResponseBody ImageUploadResponse uploadImage(@RequestParam("file") MultipartFile file) {
        ImageUploadResponse resp;
        if (!file.isEmpty()) {

            try {
                DBObject metaData = new BasicDBObject();
                UUID filename = UUID.randomUUID();;
                metaData.put("contentType", file.getContentType());
                String objectId = gridfsStore.store(file.getInputStream(), filename.toString(), file.getContentType(), metaData);

                resp = new ImageUploadResponse();
                resp.success = true;
                resp.url = ControllerLinkBuilder.linkTo(methodOn(ImageRestController.class).getById(objectId)).toString();
                return resp;
            } catch (Exception e) {
                resp = new ImageUploadResponse();
                resp.success = false;
                resp.error = e.getMessage();
                return resp;
            }
        } else {
            resp = new ImageUploadResponse();
            resp.success = false;
            resp.error = "File empty";
            return resp;
        }
    }


    @RequestMapping(value="/images", method=RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getAll() throws IOException {
        List<GridFSDBFile> all = gridfsStore.findAll();
        List<ImageModel> images = new ArrayList<ImageModel>();
        for (GridFSDBFile file : all) {
            ImageModel image = new ImageModel();
            image.id = (ObjectId) file.getId();
            image.contentType = file.getContentType();
            image.url = ControllerLinkBuilder.linkTo(methodOn(ImageRestController.class).getById(image.id.toString())).toString();
            images.add(image);
        }
        return ResponseEntity.ok().body(images);
    }

    @RequestMapping(value="/images/{id}", method=RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getById(@PathVariable("id") String id) throws IOException {
        GridFSDBFile file = gridfsStore.getById(id);
        if(file == null) {
            return ResponseEntity.notFound().build();
        } else {
            String mediaType = file.getContentType().split("/")[0];
            String subMediaType = file.getContentType().split("/")[1];
            InputStreamResource inputStream = new InputStreamResource(file.getInputStream());

            return ResponseEntity.ok()
                .contentLength(file.getLength())
                .contentType(new MediaType(mediaType, subMediaType))
                .body(new InputStreamResource(inputStream.getInputStream()));
        }
    }
}
