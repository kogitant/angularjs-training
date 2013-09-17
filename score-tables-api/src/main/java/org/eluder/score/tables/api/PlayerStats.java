package org.eluder.score.tables.api;

import org.springframework.data.annotation.Transient;

public class PlayerStats implements Domain {

    @Transient private String playerId;
    @Transient private String playerName;
    
    @Transient private double points;
    private double wins;
    private double losses;
    private double evens;
    private double pointsScored;
    private double pointsAgainst;
    
    public String getPlayerId() {
        return playerId;
    }
    
    public void setPlayerId(final String playerId) {
        this.playerId = playerId;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(final String playerName) {
        this.playerName = playerName;
    }

    public double getPoints() {
        return points;
    }
    
    public void setPoints(final int points) {
        this.points = points;
    }
    
    public double getWins() {
        return wins;
    }

    public void setWins(final int wins) {
        this.wins = wins;
    }

    public double getLosses() {
        return losses;
    }

    public void setLosses(final int losses) {
        this.losses = losses;
    }

    public double getEvens() {
        return evens;
    }
    
    public void setEvens(final int evens) {
        this.evens = evens;
    }
    
    public double getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(final int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public double getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(final int pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }
}
