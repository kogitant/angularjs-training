package org.eluder.score.tables.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tournaments")
public class Tournament extends SlugDocument {
    
    @Indexed(unique = true)
    @NotNull
    @Size(min = 2, max = 2000)
    private String name;
    
    private Map<MatchType, MatchTypeConfiguration> configurations;
    
    @Override
    public String getName() {
        return name;
    }
    
    public Tournament setName(final String name) {
        this.name = name;
        return this;
    }
    
    public Map<MatchType, MatchTypeConfiguration> getConfigurations() {
        if (configurations == null) {
            configurations = new HashMap<>();
        }
        return configurations;
    }
}
