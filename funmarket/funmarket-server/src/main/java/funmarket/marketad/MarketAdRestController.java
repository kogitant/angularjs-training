package funmarket.marketad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarketAdRestController {
    @Autowired
    MarketAdRepository marketAdRepository;

    @RequestMapping(value="/marketads", method=RequestMethod.GET)
    public @ResponseBody
    List<MarketAdModel> findAll() {
        return marketAdRepository.findAll();
    }

    @RequestMapping(value="/marketads", method=RequestMethod.POST)
    public @ResponseBody
    MarketAdModel add(@RequestBody MarketAdModel marketAdModel) {
        return marketAdRepository.save(marketAdModel);
    }

    @RequestMapping(value="/marketads/{marketAdId}", method=RequestMethod.GET)
    public @ResponseBody
    Object get(@PathVariable("marketAdId") String marketAdId) {
        MarketAdModel marketAd = marketAdRepository.findOne(marketAdId);
        if(marketAd != null) {
            return marketAd;
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/marketads/{marketAdId}", method=RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity remove(@PathVariable("marketAdId") String marketAdId) {
        if(get(marketAdId) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        marketAdRepository.delete(marketAdId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
