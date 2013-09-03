package org.eluder.score.tables.service;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.MatchType;
import org.eluder.score.tables.api.MatchTypeConfiguration;
import org.eluder.score.tables.api.Period;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.Tournament;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchServiceIT extends BaseIntegrationTest {

    @Autowired
    private MatchService matchService;
    
    @Test(expected = MethodConstraintViolationException.class)
    public void saveWithInvalidPlayerId() {
        Match match = createMatch("P1", "P2", MatchType.SERIES, 1);
        match.setBluePlayerId("12345");
        
        matchService.save(match);
    }
    
    @Test(expected = MethodConstraintViolationException.class)
    public void saveExistingWithoutVersion() {
        Match match = createMatch("P1", "P2", MatchType.SERIES, 1);
        match.getPeriods().add(new Period().setBluePlayerScore(10).setPinkPlayerScore(4));
        match = matchService.save(match);
        
        match.setVersion(null);
        matchService.save(match);
    }
    
    @Test
    public void saveValid() {
        Match match = createMatch("P1", "P2", MatchType.SERIES, 1);
        match.getPeriods().add(new Period().setBluePlayerScore(10).setPinkPlayerScore(4));
        
        matchService.save(match);
    }
    
    @Test
    public void saveForNameMatchingPlayer() {
        createPlayer("Test Player");
        Match match = createMatch("test player", "Player 2", MatchType.SERIES, 1);
        match.getPeriods().add(new Period().setBluePlayerScore(10).setPinkPlayerScore(4));
        
        match = matchService.save(match);
        Assert.assertEquals("Test Player", match.getBluePlayerName());
        Assert.assertEquals("Player 2", match.getPinkPlayerName());
    }
    
    private Match createMatch(final String blueName, final String pinkName, final MatchType matchType, final int periods) {
        Match match = new Match();
        match.setTournamentId(createTournament(matchType, periods).getId());
        match.setBluePlayerName(createPlayer(blueName).getName());
        match.setPinkPlayerName(createPlayer(pinkName).getName());
        match.setType(matchType);
        return match;
    }
    
    private Tournament createTournament(final MatchType matchType, final int periods) {
        Tournament tournament = new Tournament();
        tournament.setName("tournament");
        tournament.getConfigurations().put(matchType, new MatchTypeConfiguration().setPeriods(periods));
        mongoOperations.save(tournament);
        return tournament;
    }
    
    private Player createPlayer(final String name) {
        Player player = new Player();
        player.setName(name);
        mongoOperations.save(player);
        return player;
    }
}
