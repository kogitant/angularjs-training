package org.eluder.score.tables.service.utils;

import org.bson.types.ObjectId;
import org.eluder.score.tables.api.SlugDocument;
import org.eluder.score.tables.service.repository.SlugRepository;

import com.google.common.base.Function;

public class SlugToIdTansformer implements Function<String, String> {

    private final SlugRepository slugRepository;
    private final Class<? extends SlugDocument> entityClass;
    
    public SlugToIdTansformer(final SlugRepository slugRepository, final Class<? extends SlugDocument> entityClass) {
        this.slugRepository = slugRepository;
        this.entityClass = entityClass;
    }

    @Override
    public String apply(final String input) {
        if (ObjectId.isValid(input)) {
            return input;
        } else {
            return slugRepository.findIdBySlug(input, entityClass);
        }
    }
}
