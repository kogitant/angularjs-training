package org.eluder.score.tables.api;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "players")
@CompoundIndexes({
        @CompoundIndex(name = "search.name", def = "{ 'search.name' : 1 }",  unique = true),
        @CompoundIndex(name = "search.nameKeywords", def = "{ 'search.nameKeywords' : 1 }")
})
public class Player extends BaseDocument implements NamedDocument {
    
    private String name;
    
    @JsonIgnore
    private PlayerSearch search;
    
    @Override
    public String getName() {
        return name;
    }
    
    public Player setName(final String name) {
        this.name = name;
        return this;
    }
    
    public PlayerSearch getSearch() {
        return search;
    }
    
    public Player setSearch(final PlayerSearch search) {
        this.search = search;
        return this;
    }
}
