package org.eluder.score.tables.service.events;

import org.eluder.score.tables.api.MutableDocument;
import org.eluder.score.tables.service.utils.MongoDocumentResolver;
import org.joda.time.DateTime;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class MutableDocumentUpdateEventListener extends AbstractMongoEventListener<MutableDocument> implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void onBeforeConvert(final MutableDocument source) {
        DateTime now = new DateTime();
        if (source.getId() != null) {
            MongoOperations mongoOperations = applicationContext.getBean(MongoOperations.class);
            MongoDocumentResolver<? extends MutableDocument> documentResolver = new MongoDocumentResolver<>(mongoOperations, source.getClass(), "created");
            source.setCreated(documentResolver.apply(source.getId()).getCreated());
        } else {
            source.setCreated(now);
        }
        source.setModified(now);
    }
    
}
