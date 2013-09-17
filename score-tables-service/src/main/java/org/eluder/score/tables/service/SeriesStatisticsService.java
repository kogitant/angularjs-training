package org.eluder.score.tables.service;

import static org.springframework.data.mongodb.core.mapreduce.MapReduceOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Collections;
import java.util.List;

import org.eluder.score.tables.api.MatchType;
import org.eluder.score.tables.api.MatchTypeConfiguration;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.PlayerStats;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.comparator.PlayerStatsComparator;
import org.eluder.score.tables.service.exception.NotFoundException;
import org.eluder.score.tables.service.repository.SlugRepository;
import org.eluder.score.tables.service.utils.MongoDocumentResolver;
import org.eluder.score.tables.service.utils.PlayerStatsPointsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Service
public class SeriesStatisticsService {
    
    private static final MatchType SERIES = MatchType.SERIES;
    
    @Autowired
    private MongoOperations mongoOperations;
    
    @Autowired
    private SlugRepository slugRepository;
    
    @Autowired
    private PlayerStatsComparator playerStatsComparator;
    
    public List<PlayerStats> getTournamentStatistics(final String tournamentId) {
        Tournament tournament = getTournament(tournamentId);
        Query query = query(where("tournamentId").is(tournament.getId()).and("type").is(SERIES.toString()));
        MapReduceResults<PlayerStatsValue> results = mongoOperations.mapReduce(query, "matches", "classpath:/mapreduce/player_stats_map.js", "classpath:/mapreduce/player_stats_reduce.js", options().javaScriptMode(true).outputTypeInline(), PlayerStatsValue.class);
        List<PlayerStats> playerStats = Lists.newArrayList(transformResults(results, tournament.getConfigurations().get(SERIES)));
        Collections.sort(playerStats, playerStatsComparator);
        return playerStats;
    }
    
    private Iterable<PlayerStats> transformResults(final Iterable<PlayerStatsValue> results, final MatchTypeConfiguration configuration) {
        Function<PlayerStatsValue, PlayerStats> transformer = Functions.compose(
                new PlayerStatsPointsTransformer(configuration), new FlattenPlayerStats(mongoOperations)
        );
        return Iterables.transform(results, transformer);
    }
    
    private Tournament getTournament(final String tournamentId) {
        Tournament tournament = slugRepository.findOneByIdOrSlug(tournamentId, Tournament.class, ImmutableList.of("id", "configurations"));
        if (tournament == null) {
            throw new NotFoundException(Tournament.class, tournamentId);
        }
        return tournament;
    }
    
    @Document
    private static class PlayerStatsValue {
        @Id public String id;
        public PlayerStats value;
    }
    
    private static class FlattenPlayerStats implements Function<PlayerStatsValue, PlayerStats> {
        
        private final MongoDocumentResolver<Player> playerResolver;
        
        public FlattenPlayerStats(final MongoOperations mongoOperations) {
            this.playerResolver = new MongoDocumentResolver<>(mongoOperations, Player.class, "name");
        }
        
        @Override
        public PlayerStats apply(final PlayerStatsValue input) {
            PlayerStats playerStats = input.value;
            playerStats.setPlayerId(input.id);
            playerStats.setPlayerName(playerResolver.apply(input.id).getName());
            return playerStats;
        }
    }
}
