package org.eluder.score.tables.api.query;

import java.util.ArrayList;
import java.util.List;

import org.eluder.score.tables.api.Domain;
import org.springframework.data.domain.Sort;

public class BasicQuery implements Domain {

    private Sort sort;
    private String value;
    
    private final List<String> tournamentIds = new ArrayList<>();
    private final List<String> playerIds = new ArrayList<>();
    
    public BasicQuery() { }
    
    public BasicQuery(final String value) {
        setValue(value);
    }
    
    public Sort getSort() {
        return sort;
    }
    
    public BasicQuery setSort(final Sort sort) {
        this.sort = sort;
        return this;
    }
    
    public String getValue() {
        return value;
    }
    
    public BasicQuery setValue(final String value) {
        this.value = value;
        return this;
    }
    
    public List<String> getTournamentIds() {
        return tournamentIds;
    }
    
    public BasicQuery addTournamentIds(final String... tournamentIds) {
        for (String tournamentId : tournamentIds) {
            this.tournamentIds.add(tournamentId);
        }
        return this;
    }
    
    public List<String> getPlayerIds() {
        return playerIds;
    }
    
    public BasicQuery addPlayerIds(final String... playerIds) {
        for (String playerId : playerIds) {
            this.playerIds.add(playerId);
        }
        return this;
    }
    
}
