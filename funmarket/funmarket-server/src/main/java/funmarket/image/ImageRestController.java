package funmarket.image;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.imgscalr.Scalr.resize;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ImageRestController {
    final static Integer IMAGE_MAX_SIZE = 1024;
    final static Integer IMAGE_THUMB_MAX_SIZE = 256;

    @Autowired
    GridfsStore gridfsStore;

    @RequestMapping(value="/images", method=RequestMethod.POST)
    public @ResponseBody ImageUploadResponse uploadImage(@RequestParam("file") MultipartFile file) {
        ImageUploadResponse resp;
        if (!file.isEmpty()) {
            try {
                BufferedImage img = ImageIO.read(file.getInputStream());
                file.getInputStream().close();

                DBObject metaData = new BasicDBObject();
                UUID filename = UUID.randomUUID();
                metaData.put("contentType", file.getContentType());
                String imageId = gridfsStore.store(
                    resizeImageAsStream(img, IMAGE_MAX_SIZE, IMAGE_MAX_SIZE),
                    filename.toString(),
                    file.getContentType(),
                    metaData);

                String thumbId = gridfsStore.store(
                    resizeImageAsStream(img, IMAGE_THUMB_MAX_SIZE, IMAGE_THUMB_MAX_SIZE),
                    "thumb-" + filename.toString(),
                    file.getContentType(),
                    metaData);

                resp = new ImageUploadResponse();
                resp.success = true;
                resp.imageUrl = ControllerLinkBuilder.linkTo(methodOn(ImageRestController.class).getById(imageId)).toString();
                resp.thumbnailUrl = ControllerLinkBuilder.linkTo(methodOn(ImageRestController.class).getById(thumbId)).toString();
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

    private BufferedImage resizeImage(BufferedImage img, int width, int height) {
        if (width == 0 && height == 0) {
            return img;
        }
        Mode mode = Mode.AUTOMATIC;
        if (width == 0) {
            mode = Mode.FIT_TO_HEIGHT;
        } else if (height == 0) {
            mode = Mode.FIT_TO_WIDTH;
        } else {
            double ratio = (double)img.getWidth() / img.getHeight();
            double newWidth = height * ratio;
            double newHeight = width / ratio;
            if (newWidth >= width) {
                mode = Mode.FIT_TO_HEIGHT;
            } else if (newHeight >= height) {
                mode = Mode.FIT_TO_WIDTH;
            }
        }
        return resize(img, Scalr.Method.QUALITY, mode, width, height);
    }

    private InputStream resizeImageAsStream(BufferedImage img, int maxWidth, int maxHeight) throws IOException {
        img = resizeImage(img, maxWidth, maxHeight);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
