package org.eluder.score.tables.service.repository;

import org.eluder.score.tables.api.Tournament;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TournamentRepository extends PagingAndSortingRepository<Tournament, String> {

}
