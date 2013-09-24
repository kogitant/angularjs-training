package org.eluder.score.tables.rest.springmvc;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.PlayerStats;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.MatchService;
import org.eluder.score.tables.service.SeriesStatisticsService;
import org.eluder.score.tables.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;
    
    @Autowired
    private MatchService matchService;
    
    @Autowired
    private SeriesStatisticsService seriesStatisticsService;
    
    @RequestMapping(method = GET)
    public @ResponseBody List<Tournament> findAll() {
        return tournamentService.findAll();
    }
    
    @RequestMapping(method = POST)
    public @ResponseBody Tournament save(@RequestBody final Tournament tournament) {
        return tournamentService.save(tournament);
    }
    
    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody Tournament findOne(@PathVariable("id") final String id) {
        return tournamentService.findOne(id);
    }
    
    @RequestMapping(value = "/{id}/statistics", method = GET)
    public @ResponseBody List<PlayerStats> getStatistics(@PathVariable("id") final String id) {
        return seriesStatisticsService.getTournamentStatistics(id);
    }
    
    @RequestMapping(value = "/{id}/matches", method = GET)
    public @ResponseBody List<Match> getMatches(@PathVariable("id") final String id) {
        return matchService.find(new BasicQuery().addTournamentIds(id).setSort(new Sort("created")));
    }
}
