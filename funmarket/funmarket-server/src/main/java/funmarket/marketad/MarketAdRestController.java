package funmarket.marketad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MarketAdRestController {
    @Autowired
    MarketAdRepository marketAdRepository;

    @RequestMapping(value="/marketads", method=RequestMethod.GET)
    public @ResponseBody
    List<MarketAd> findAll() {
        return marketAdRepository.findAll();
    }

    @RequestMapping("/marketads/{marketAdId}")
    public @ResponseBody
    MarketAd findPersonById(@PathVariable("marketAdId") String marketAdId) {
        return marketAdRepository.findOne(marketAdId);
    }

    /*
    @RequestMapping(value="/marketads", method=RequestMethod.POST)
    public @ResponseBody
    MarketAd add(@RequestBody MarketAd marketAd) {
        return marketAdRepository.save(marketAd);
    }
    */

    @RequestMapping(value="/marketads", method=RequestMethod.POST)
    public @ResponseBody MarketAd upload(@RequestParam("img") MultipartFile file,
                                         @RequestParam(value="title", required=false) String title,
                                         @RequestParam(value="description", required=false) String description,
                                         @RequestParam(value="priceCents", required=false) Integer priceCents,
                                         @RequestParam(value="email", required=false) String email,
                                         @RequestParam(value="phone", required=false) String phone) throws IOException {
        byte[] bytes;

        if (!file.isEmpty()) {
            bytes = file.getBytes();
        }

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), title));

        MarketAd marketAd = new MarketAd(
            title,
            description,
            priceCents,
            email,
            phone
        );
        return marketAdRepository.save(marketAd);
    }

}
