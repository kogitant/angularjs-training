package org.eluder.score.tables.rest.springmvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;
    
    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody Match findOne(@PathVariable("id") final String id) {
        return matchService.findOne(id);
    }
    
    @RequestMapping(method = POST)
    public @ResponseBody Match save(@RequestBody final Match match) {
        return matchService.save(match);
    }
    
    @RequestMapping(method = GET)
    public @ResponseBody List<Match> findAll() {
        return matchService.findAll();
    }
}
