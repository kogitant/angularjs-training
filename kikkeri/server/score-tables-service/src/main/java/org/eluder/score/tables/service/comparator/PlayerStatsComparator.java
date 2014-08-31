package org.eluder.score.tables.service.comparator;

import java.util.Comparator;

import org.eluder.score.tables.api.PlayerStats;
import org.springframework.stereotype.Component;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Component
public class PlayerStatsComparator implements Comparator<PlayerStats> {
    
    @Override
    public int compare(final PlayerStats o1, final PlayerStats o2) {
        return ComparisonChain.start()
            .compare(o1.getPoints(), o2.getPoints(), Ordering.natural().reverse())
            .compare(o1.getWins(), o2.getWins(), Ordering.natural().reverse())
            .compare(o1.getPointsScored(), o2.getPointsScored(), Ordering.natural().reverse())
            .compare(o1.getLosses(), o2.getLosses(), Ordering.natural())
            .compare(o1.getPointsAgainst(), o2.getPointsAgainst(), Ordering.natural())
            .result();
    }
}
