package org.eluder.score.tables.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.repository.MatchRepository;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.eluder.score.tables.service.repository.SlugRepository;
import org.eluder.score.tables.service.utils.BasicQuerySlugTransformer;
import org.eluder.score.tables.service.utils.MongoDocumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Service
@Validated
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private SlugRepository slugRepository;
    
    @Autowired
    private MongoOperations mongoOperations;
    
    public Match findOne(final String id) {
        return compose(matchRepository.findOne(id));
    }
    
    public List<Match> findAll() {
        Iterable<Match> matches = matchRepository.findAll(new Sort(Direction.ASC, "created"));
        return compose(matches);
    }
    
    public List<Match> find(final BasicQuery query) {
        Iterable<Match> matches = matchRepository.find(new BasicQuerySlugTransformer(slugRepository).apply(query));
        return compose(matches);
    }
    
    
    public Match save(@NotNull @Valid final Match match) {        
        match.setBluePlayerId(getPreparedPlayerId(match.getBluePlayerId(), match.getBluePlayerName()));
        match.setPinkPlayerId(getPreparedPlayerId(match.getPinkPlayerId(), match.getPinkPlayerName()));
        return compose(matchRepository.save(match));
    }
    
    private String getPreparedPlayerId(final String playerId, final String playerName) {
        if (playerId != null) {
            return (ObjectId.isValid(playerId) ? playerId : slugRepository.findIdBySlug(playerId, Player.class));
        }
        Player player = playerRepository.findBySearchName(new BasicQuery().setValue(playerName));
        if (player != null) {
            return player.getId();
        }
        player = new Player();
        player.setName(playerName);
        player = playerRepository.save(player);
        return player.getId();
    }
    
    private Match compose(final Match match) {
        return new MatchComposer(mongoOperations).apply(match);
    }
    
    private List<Match> compose(final Iterable<Match> matches) {
        return ImmutableList.copyOf(Iterables.transform(matches, new MatchComposer(mongoOperations)));
    }
    
    private static class MatchComposer implements Function<Match, Match> {
        
        private final MongoDocumentResolver<Player> playerResolver;
        
        public MatchComposer(final MongoOperations mongoOperations) {
            this.playerResolver = new MongoDocumentResolver<>(mongoOperations, Player.class, "name");
        }
        
        @Override
        public Match apply(final Match input) {
            if (input == null) {
                return null;
            }
            if (input.getBluePlayerId() != null) {
                input.setBluePlayerName(playerResolver.apply(input.getBluePlayerId()).getName());
            }
            if (input.getPinkPlayerId() != null) {
                input.setPinkPlayerName(playerResolver.apply(input.getPinkPlayerId()).getName());
            }
            return input;
        }
    }
}
