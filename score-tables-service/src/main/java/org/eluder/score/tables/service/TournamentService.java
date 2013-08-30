package org.eluder.score.tables.service;

import java.util.List;

import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    
    public Tournament findOne(final String id) {
        return tournamentRepository.findOne(id);
    }
    
    public List<Tournament> findAll() {
        return ImmutableList.copyOf(tournamentRepository.findAll());
    }
    
    public Tournament save(final Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
}
