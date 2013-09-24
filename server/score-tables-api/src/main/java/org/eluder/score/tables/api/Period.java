package org.eluder.score.tables.api;

public class Period implements Domain {

    private int bluePlayerScore;
    private int pinkPlayerScore;

    public int getBluePlayerScore() {
        return bluePlayerScore;
    }
    
    public Period setBluePlayerScore(final int bluePlayerScore) {
        this.bluePlayerScore = bluePlayerScore;
        return this;
    }
    
    public int getPinkPlayerScore() {
        return pinkPlayerScore;
    }
    
    public Period setPinkPlayerScore(final int pinkPlayerScore) {
        this.pinkPlayerScore = pinkPlayerScore;
        return this;
    }
}
