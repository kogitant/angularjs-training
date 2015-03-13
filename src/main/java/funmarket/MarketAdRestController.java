package funmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarketAdRestController {

    @Autowired
    MarketAdRepository marketAdRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/marketads/{marketAdId}")
    public @ResponseBody
    MarketAd findPersonById(@PathVariable("marketAdId") String marketAdId) {
        return marketAdRepository.findOne(marketAdId);
    }

    @RequestMapping("/marketads/")
    public @ResponseBody
    List<MarketAd> findAll() {
        return marketAdRepository.findAll();
    }

    @RequestMapping(value="/marketads/", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity add(@RequestBody MarketAd marketAd) {
        mongoTemplate.save(marketAd);
        return new ResponseEntity<String>("MarketAd saved", HttpStatus.OK);
    }
}
