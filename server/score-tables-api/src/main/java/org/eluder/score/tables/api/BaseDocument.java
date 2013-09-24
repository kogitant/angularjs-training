package org.eluder.score.tables.api;

import org.springframework.data.annotation.Id;

public abstract class BaseDocument implements Domain {

    @Id
    private String id;
    
    public String getId() {
        return id;
    }
    
    public BaseDocument setId(final String id) {
        this.id = id;
        return this;
    }
    
}
