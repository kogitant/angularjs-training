package org.eluder.score.tables.service;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.MatchTypeConfiguration;
import org.eluder.score.tables.api.MatchType;
import org.eluder.score.tables.api.Period;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.Tournament;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchServiceIT extends BaseIntegrationTest {

    @Autowired
    private MatchService matchService;
    
    @Test
    public void save() {
        Match match = new Match();
        match.setTournamentId(createTournament(MatchType.SERIES, 1).getId());
        match.setBluePlayerName(createPlayer("P1").getName());
        match.setPinkPlayerName(createPlayer("P2").getName());
        match.setType(MatchType.SERIES);
        match.getPeriods().add(new Period().setBluePlayerScore(10).setPinkPlayerScore(4));
        
        matchService.save(match);
    }
    
    private Tournament createTournament(final MatchType matchType, final int periods) {
        Tournament tournament = new Tournament();
        tournament.setName("tournament");
        tournament.getConfigurations().put(matchType, new MatchTypeConfiguration().setPeriods(periods));
        mongoTemplate.save(tournament);
        return tournament;
    }
    
    private Player createPlayer(final String name) {
        Player player = new Player();
        player.setName(name);
        mongoTemplate.save(player);
        return player;
    }
}
