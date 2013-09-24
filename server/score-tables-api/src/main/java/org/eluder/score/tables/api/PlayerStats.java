package org.eluder.score.tables.api;


public class PlayerStats implements Domain {

    private String playerId;
    private String playerName;
    
    private int points;
    private int wins;
    private int losses;
    private int evens;
    private int pointsScored;
    private int pointsAgainst;
    
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

    public int getPoints() {
        return points;
    }
    
    public void setPoints(final int points) {
        this.points = points;
    }
    
    public int getWins() {
        return wins;
    }

    public void setWins(final int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(final int losses) {
        this.losses = losses;
    }

    public int getEvens() {
        return evens;
    }
    
    public void setEvens(final int evens) {
        this.evens = evens;
    }
    
    public int getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(final int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public int getPointsAgainst() {
        return pointsAgainst;
    }

    public void setPointsAgainst(final int pointsAgainst) {
        this.pointsAgainst = pointsAgainst;
    }
}
