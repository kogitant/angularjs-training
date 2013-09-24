package org.eluder.score.tables.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.ImmutableList;

@Service
@Validated
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private MongoOperations mongoTemplate;
    
    public Player findOne(final String id) {
        return playerRepository.findOne(id);
    }
    
    public List<Player> findAll() {
        return ImmutableList.copyOf(playerRepository.findAll());
    }
    
    public Player save(@NotNull @Valid final Player player) {
        return playerRepository.save(player);
    }
    
    public List<Player> findByNameKeywords(final BasicQuery query) {
        return ImmutableList.copyOf(playerRepository.findBySearchNameKeywords(query));
    }
}
