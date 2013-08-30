package org.eluder.score.tables.service.events;

import org.eluder.score.tables.api.MutableDocument;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

@Component
public class MutableDocumentUpdateEventListener extends AbstractMongoEventListener<MutableDocument> {

    @Override
    public void onBeforeConvert(final MutableDocument source) {
        DateTime now = new DateTime();
        if (source.getCreated() == null) {
            source.setCreated(now);
        }
        source.setModified(now);
    }
    
}
