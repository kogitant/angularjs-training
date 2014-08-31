package org.eluder.score.tables.service.events;

import org.bson.types.ObjectId;
import org.eluder.score.tables.api.SlugDocument;
import org.eluder.score.tables.service.repository.SlugRepository;
import org.eluder.score.tables.service.utils.SlugCreator;
import org.springframework.stereotype.Component;

@Component
public class SlugDocumentUpdateEventListener extends ContextAwareMongoListener<SlugDocument> {
    
    @Override
    public void onBeforeConvert(final SlugDocument source) {
        if (source.getId() != null && !ObjectId.isValid(source.getId())) {
            SlugRepository slugRepository = getBean(SlugRepository.class);
            source.setId(slugRepository.findIdBySlug(source.getId(), source.getClass()));
        }
        String slug = new SlugCreator().apply(source.getName());
        source.setSlug(slug);
    }
}
