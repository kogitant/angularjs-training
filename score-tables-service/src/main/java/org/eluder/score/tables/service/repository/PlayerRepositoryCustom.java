package org.eluder.score.tables.service.repository;

import java.util.List;

import org.eluder.score.tables.api.Player;

public interface PlayerRepositoryCustom {

    Player findBySearchName(String name);
    
    List<Player> findBySearchNameKeywords(String name);
    
}
