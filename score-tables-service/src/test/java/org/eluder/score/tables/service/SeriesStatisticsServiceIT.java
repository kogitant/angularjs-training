package org.eluder.score.tables.service;


import java.util.List;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.MatchType;
import org.eluder.score.tables.api.MatchTypeConfiguration;
import org.eluder.score.tables.api.Period;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.PlayerStats;
import org.eluder.score.tables.api.Tournament;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableList;

public class SeriesStatisticsServiceIT extends BaseIntegrationTest {
    
    @Autowired
    private SeriesStatisticsService seriesStatisticsService;
    
    private String tournament;
    private String player1;
    private String player2;
    private String player3;
    
    @Before
    public void init() {
        Tournament tournament = new Tournament();
        tournament.setName("tournamet");
        tournament.getConfigurations().put(
                MatchType.SERIES,
                new MatchTypeConfiguration().setPeriods(2).setPointsForWin(2).setPointsForEven(1)
        );
        mongoOperations.save(tournament);
        this.tournament = tournament.getId();
        
        Player p1 = new Player().setName("p1");
        mongoOperations.save(p1);
        this.player1 = p1.getId();
        
        Player p2 = new Player().setName("p2");
        mongoOperations.save(p2);
        this.player2 = p2.getId();

        Player p3 = new Player().setName("p3");
        mongoOperations.save(p3);
        this.player3 = p3.getId();
    }
    
    @Test
    public void testGetSimpleTournamentStatistics() {
        mongoOperations.save(match(player1, player2, period(10, 3), period(10, 3)));
        mongoOperations.save(match(player2, player1, period(6, 10), period(2, 10)));
        List<PlayerStats> stats = seriesStatisticsService.getTournamentStatistics(tournament);
        Assert.assertEquals(2, stats.size());
        Assert.assertEquals("p1", stats.get(0).getPlayerName());
        Assert.assertEquals(4, stats.get(0).getPoints());
        Assert.assertEquals("p2", stats.get(1).getPlayerName());
        Assert.assertEquals(0, stats.get(1).getPoints());
    }
    
    private Match match(final String blueId, final String pinkId, final Period... periods) {
        Match match = new Match();
        match.setTournamentId(tournament);
        match.setBluePlayerId(blueId);
        match.setPinkPlayerId(pinkId);
        match.setType(MatchType.SERIES);
        match.getPeriods().addAll(ImmutableList.copyOf(periods));
        return match;
    }
    
    private Period period(final int blueScore, final int pinkScore) {
        return new Period().setBluePlayerScore(blueScore).setPinkPlayerScore(pinkScore);
    }
}
