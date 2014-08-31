package org.eluder.score.tables.service.repository;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.query.BasicQuery;

public interface PlayerRepositoryCustom {

    Player findBySearchName(BasicQuery query);
    
    Iterable<Player> findBySearchNameKeywords(BasicQuery query);
    
}
