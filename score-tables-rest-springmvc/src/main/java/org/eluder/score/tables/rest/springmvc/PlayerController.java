package org.eluder.score.tables.rest.springmvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody Player findOne(final String id) {
        return playerService.findOne(id);
    }
    
    @RequestMapping(method = GET)
    public @ResponseBody List<Player> findAll() {
        return playerService.findAll();
    }
    
    @RequestMapping(method = POST)
    public @ResponseBody Player save(@RequestBody final Player player) {
        return playerService.save(player);
    }
}
