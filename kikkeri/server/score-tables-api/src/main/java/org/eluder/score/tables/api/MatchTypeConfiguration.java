package org.eluder.score.tables.api;

public class MatchTypeConfiguration implements Domain {

    private int periods;
    private int pointsForWin;
    private int pointsForLoss;
    private int pointsForEven;
    
    public int getPeriods() {
        return periods;
    }
    
    public MatchTypeConfiguration setPeriods(final int periods) {
        this.periods = periods;
        return this;
    }
    
    public int getPointsForWin() {
        return pointsForWin;
    }
    
    public MatchTypeConfiguration setPointsForWin(final int pointsForWin) {
        this.pointsForWin = pointsForWin;
        return this;
    }
    
    public int getPointsForLoss() {
        return pointsForLoss;
    }
    
    public MatchTypeConfiguration setPointsForLoss(final int pointsForLoss) {
        this.pointsForLoss = pointsForLoss;
        return this;
    }
    
    public int getPointsForEven() {
        return pointsForEven;
    }
    
    public MatchTypeConfiguration setPointsForEven(final int pointsForEven) {
        this.pointsForEven = pointsForEven;
        return this;
    }
}
