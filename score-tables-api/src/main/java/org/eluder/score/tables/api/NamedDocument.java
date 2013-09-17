package org.eluder.score.tables.api;

public interface NamedDocument extends Domain {

    String getId();
    
    String getName();
    
    String getSlug();
}
