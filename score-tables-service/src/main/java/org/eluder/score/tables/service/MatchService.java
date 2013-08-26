package org.eluder.score.tables.service;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.exception.NotFoundException;
import org.eluder.score.tables.service.exception.ValidationException;
import org.eluder.score.tables.service.repository.MatchRepository;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.eluder.score.tables.service.repository.TournamentRepository;
import org.eluder.score.tables.service.utils.LowerCaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
    public Match findOne(final String id) {
        return matchRepository.findOne(id);
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
        
        return matchRepository.save(match);
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
        Player player = playerRepository.findBySearchName(new LowerCaser().apply(playerName));
        if (player != null) {
            return player.getId();
        }
        player = new Player();
        player.setName(playerName);
        player = playerRepository.save(player);
        return player.getId();
    }
}
