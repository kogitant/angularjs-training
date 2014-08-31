package org.eluder.score.tables.service.utils;

import org.eluder.score.tables.api.MatchTypeConfiguration;
import org.eluder.score.tables.api.PlayerStats;

import com.google.common.base.Function;

public class PlayerStatsPointsTransformer implements Function<PlayerStats, PlayerStats> {

    private final MatchTypeConfiguration configuration;
    
    public PlayerStatsPointsTransformer(final MatchTypeConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public PlayerStats apply(final PlayerStats input) {
        int points = 0;
        points += configuration.getPointsForWin() * input.getWins();
        points += configuration.getPointsForLoss() * input.getLosses();
        points += configuration.getPointsForEven() * input.getEvens();
        input.setPoints(points);
        return input;
    }
    
}
