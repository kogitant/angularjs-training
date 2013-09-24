package org.eluder.score.tables.service.repository;

import org.eluder.score.tables.api.Match;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MatchRepository extends PagingAndSortingRepository<Match, String>, MatchRepositoryCustom {

}
