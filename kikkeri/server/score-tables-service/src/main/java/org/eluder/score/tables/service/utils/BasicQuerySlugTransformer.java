package org.eluder.score.tables.service.utils;

import java.util.List;

import org.eluder.score.tables.api.Player;
import org.eluder.score.tables.api.Tournament;
import org.eluder.score.tables.api.query.BasicQuery;
import org.eluder.score.tables.service.repository.SlugRepository;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class BasicQuerySlugTransformer implements Function<BasicQuery, BasicQuery> {


    private final SlugRepository slugRepository;
    
    public BasicQuerySlugTransformer(final SlugRepository slugRepository) {
        this.slugRepository = slugRepository;
    }

    @Override
    public BasicQuery apply(final BasicQuery input) {
        List<String> tournamentIds = ImmutableList.copyOf(Lists.transform(input.getTournamentIds(), new SlugToIdTansformer(slugRepository, Tournament.class)));
        List<String> playerIds = ImmutableList.copyOf(Lists.transform(input.getPlayerIds(), new SlugToIdTansformer(slugRepository, Player.class)));
        input.getTournamentIds().clear();
        input.getTournamentIds().addAll(tournamentIds);
        input.getPlayerIds().clear();
        input.getPlayerIds().addAll(playerIds);
        return input;
    }
    
}
