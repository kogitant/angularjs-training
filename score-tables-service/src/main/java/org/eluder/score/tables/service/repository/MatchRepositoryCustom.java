package org.eluder.score.tables.service.repository;

import org.eluder.score.tables.api.Match;
import org.eluder.score.tables.api.query.BasicQuery;

public interface MatchRepositoryCustom {

    Iterable<Match> find(BasicQuery query);
    
}
