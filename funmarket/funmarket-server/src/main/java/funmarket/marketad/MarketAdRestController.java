package funmarket.marketad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/marketads", method=RequestMethod.POST)
    public @ResponseBody
    MarketAd add(@RequestBody MarketAd marketAd) {
        return marketAdRepository.save(marketAd);
    }
}
