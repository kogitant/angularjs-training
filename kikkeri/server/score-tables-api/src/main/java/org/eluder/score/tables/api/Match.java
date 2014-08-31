package org.eluder.score.tables.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.eluder.score.tables.api.validation.AnyNotNull;
import org.eluder.score.tables.api.validation.AnyNotNulls;
import org.eluder.score.tables.api.validation.ExistingReference;
import org.eluder.score.tables.api.validation.ValidPeriods;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matches")
@CompoundIndexes(
        @CompoundIndex(name = "tournament_type", def = "{ 'tournamentId' : 1, 'type' : 1 }")
)
@AnyNotNulls({
        @AnyNotNull({ "bluePlayerId", "bluePlayerName" }),
        @AnyNotNull({ "pinkPlayerId", "pinkPlayerName" })
})
@ValidPeriods
public class Match extends MutableDocument {
    
    @NotNull
    @ExistingReference(Tournament.class)
    private String tournamentId;
    
    @Indexed
    @ExistingReference(Player.class)
    private String bluePlayerId;
    
    @Transient
    private String bluePlayerName;
    
    @Indexed
    @ExistingReference(Player.class)
    private String pinkPlayerId;
    
    @Transient
    private String pinkPlayerName;
    
    @NotNull
    private MatchType type;
    
    private List<Period> periods;

    public String getTournamentId() {
        return tournamentId;
    }
    
    public void setTournamentId(final String tournamentId) {
        this.tournamentId = tournamentId;
    }
    
    public String getBluePlayerId() {
        return bluePlayerId;
    }
    
    public void setBluePlayerId(final String bluePlayerId) {
        this.bluePlayerId = bluePlayerId;
    }

    public String getBluePlayerName() {
        return bluePlayerName;
    }
    
    public void setBluePlayerName(final String bluePlayerName) {
        this.bluePlayerName = bluePlayerName;
    }
    
    public String getPinkPlayerId() {
        return pinkPlayerId;
    }
    
    public void setPinkPlayerId(final String pinkPlayerId) {
        this.pinkPlayerId = pinkPlayerId;
    }
    
    public String getPinkPlayerName() {
        return pinkPlayerName;
    }
    
    public void setPinkPlayerName(final String pinkPlayerName) {
        this.pinkPlayerName = pinkPlayerName;
    }
    
    public MatchType getType() {
        return type;
    }
    
    public void setType(final MatchType type) {
        this.type = type;
    }
    
    public List<Period> getPeriods() {
        if (periods == null) {
            periods = new ArrayList<Period>();
        }
        return periods;
    }
    
    public int getBluePlayerPointsScored() {
        int score = 0;
        for (Period period : getPeriods()) {
            score += period.getBluePlayerScore();
        }
        return score;
    }
    
    public int getPinkPlayerPointsScored() {
        int score = 0;
        for (Period period : getPeriods()) {
            score += period.getPinkPlayerScore();
        }
        return score;
    }
    
    public int getBluePlayerPeriodsWon() {
        int wonPeriods = 0;
        for (Period period : getPeriods()) {
            boolean won = period.getBluePlayerScore() > period.getPinkPlayerScore();
            wonPeriods += (won ? 1 : 0);
        }
        return wonPeriods;
    }
    
    public int getPinkPlayerPeriodsWon() {
        int wonPeriods = 0;
        for (Period period : getPeriods()) {
            boolean won = period.getPinkPlayerScore() > period.getBluePlayerScore();
            wonPeriods += (won ? 1 : 0);
        }
        return wonPeriods;
    }
}
