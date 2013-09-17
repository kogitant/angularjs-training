package org.eluder.score.tables.service.events;

import org.eluder.score.tables.api.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import com.mongodb.DBObject;

@Component
public class LoggingDocumentEventListener extends AbstractMongoEventListener<BaseDocument> implements Ordered {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDocumentEventListener.class);
    
    @Override
    public void onAfterSave(final BaseDocument source, final DBObject dbo) {
        LOGGER.info("Saved document: {}", dbo);
    }
    
    @Override
    public void onAfterLoad(final DBObject dbo) {
        LOGGER.debug("Loaded document: {}", dbo);
    }
    
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

}
