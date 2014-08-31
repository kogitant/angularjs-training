package org.eluder.score.tables.service.repository;

import org.eluder.score.tables.api.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, String>, PlayerRepositoryCustom {
    
}
