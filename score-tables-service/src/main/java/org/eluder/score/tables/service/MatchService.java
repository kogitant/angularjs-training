package org.eluder.score.tables.service;

import java.util.List;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.exception.NotFoundException;
import org.eluder.score.tables.service.exception.ValidationException;
import org.eluder.score.tables.service.repository.MatchRepository;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.eluder.score.tables.service.repository.TournamentRepository;
import org.eluder.score.tables.service.utils.MongoDocumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
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
        Iterable<Match> matches = matchRepository.find(query);
        return compose(matches);
    }
    
    public Match save(final Match match) {
        if (match.getTournamentId() == null) {
            throw new ValidationException("Tournament must be defined");
        }
        if (match.getType() == null) {
            throw new ValidationException("Match type must be defined");
        }
        if (match.getBluePlayerId() == null && match.getBluePlayerName() == null) {
            throw new ValidationException("Blue player must be defined");
        }
        if (match.getPinkPlayerId() == null && match.getPinkPlayerName() == null) {
            throw new ValidationException("Pink player must be defined");
        }
        if (match.getBluePlayerId() != null && playerRepository.exists(match.getBluePlayerId())) {
            throw new NotFoundException(Player.class, match.getBluePlayerId());
        }
        if (match.getPinkPlayerId() != null && playerRepository.exists(match.getPinkPlayerId())) {
            throw new NotFoundException(Player.class, match.getPinkPlayerId());
        }
        Tournament tournament = tournamentRepository.findOne(match.getTournamentId());
        if (tournament == null) {
            throw new NotFoundException(Tournament.class, match.getTournamentId());
        }
        verifyPeriods(match, tournament);
        
        match.setBluePlayerId(getPreparedPlayerId(match.getBluePlayerId(), match.getBluePlayerName()));
        match.setPinkPlayerId(getPreparedPlayerId(match.getPinkPlayerId(), match.getPinkPlayerName()));
        
        return compose(matchRepository.save(match));
    }
    
    private void verifyPeriods(final Match match, final Tournament tournament) {
        int played = match.getPeriods().size();
        int required = tournament.getConfigurations().get(match.getType()).getPeriods();
        if (match.getType().isBestOf()) {
            int requiredWins = required / 2 + 1;
            if (match.getBluePlayerPeriodsWon() != requiredWins && match.getPinkPlayerPeriodsWon() != requiredWins) {
                throw new ValidationException(match.getType() + " match played for best of " + required + " but neither player has " + requiredWins + " won periods");
            }
        } else if (played != required) {
            throw new ValidationException(match.getType() + " match played for " + required + " periods but only " + played + " periods has been played");
        }
    }
    
    private String getPreparedPlayerId(final String playerId, final String playerName) {
        if (playerId != null) {
            return playerId;
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
