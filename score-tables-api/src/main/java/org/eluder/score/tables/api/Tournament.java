package org.eluder.score.tables.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournaments")
public class Tournament extends BaseDocument {
    
    @Indexed(unique = true)
    private String name;
    
    private Map<MatchType, MatchTypeConfiguration> configurations;
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Map<MatchType, MatchTypeConfiguration> getConfigurations() {
        if (configurations == null) {
            configurations = new HashMap<>();
        }
        return configurations;
    }
}
