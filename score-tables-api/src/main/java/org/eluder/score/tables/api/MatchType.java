package org.eluder.score.tables.api;

public enum MatchType {

    SERIES(false),
    PLAYOFF(true),
    BRONZE(true),
    GOLD(true);
    
    private boolean bestOf;
    
    private MatchType(final boolean bestOf) {
        this.bestOf = bestOf;
    }
    
    public boolean isBestOf() {
        return bestOf;
    }
    
}
