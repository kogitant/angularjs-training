package org.eluder.score.tables.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public abstract class BaseDocument implements Domain {

    @Id
    private String id;
    
    @Version
    private Integer version;
    
    public String getId() {
        return id;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(final Integer version) {
        this.version = version;
    }
    
}
