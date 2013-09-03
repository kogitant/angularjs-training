package org.eluder.score.tables.api;

import org.eluder.score.tables.api.validation.VersionedDocument;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;

@VersionedDocument
public abstract class MutableDocument extends BaseDocument {

    @Version
    private Integer version;
    
    @Indexed
    private DateTime created;
    
    @Indexed
    private DateTime modified;
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(final Integer version) {
        this.version = version;
    }
    
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
