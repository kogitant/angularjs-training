package org.eluder.score.tables.api;

import java.util.ArrayList;
import java.util.List;

public class PlayerSearch implements Domain {

    private String name;    
    private List<String> nameKeywords;
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public List<String> getNameKeywords() {
        if (nameKeywords == null) {
            nameKeywords = new ArrayList<>();
        }
        return nameKeywords;
    }
}
