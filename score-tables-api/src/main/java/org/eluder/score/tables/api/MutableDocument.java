package org.eluder.score.tables.api;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.index.Indexed;

public abstract class MutableDocument extends BaseDocument {

    @Indexed
    private DateTime created;
    
    @Indexed
    private DateTime modified;
    
    public DateTime getCreated() {
        return created;
    }
    
    public void setCreated(final DateTime created) {
        this.created = created;
    }
    
    public DateTime getModified() {
        return modified;
    }
    
    public void setModified(final DateTime modified) {
        this.modified = modified;
    }
}
