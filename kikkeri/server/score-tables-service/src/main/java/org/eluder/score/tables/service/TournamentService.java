package org.eluder.score.tables.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.service.repository.SlugRepository;
import org.eluder.score.tables.service.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.ImmutableList;

@Service
@Validated
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private SlugRepository slugRepository;
    
    public Tournament findOne(final String id) {
        return slugRepository.findOneByIdOrSlug(id, Tournament.class);
    }
    
    public List<Tournament> findAll() {
        return ImmutableList.copyOf(tournamentRepository.findAll());
    }
    
    public Tournament save(@NotNull @Valid final Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
}
