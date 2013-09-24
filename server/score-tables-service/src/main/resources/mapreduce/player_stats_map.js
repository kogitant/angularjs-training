function () {
    var bluePointsScored = 0;
    var pinkPointsScored = 0;
    var bluePeriodsWon = 0;
    var pinkPeriodsWon = 0;
    
    for (var i = 0; i < this.periods.length; i++) {
        var period = this.periods[i];
        bluePointsScored += period.bluePlayerScore;
        pinkPointsScored += period.pinkPlayerScore;
        bluePeriodsWon += (period.bluePlayerScore > period.pinkPlayerScore) ? 1 : 0;
        pinkPeriodsWon += (period.pinkPlayerScore > period.bluePlayerScore) ? 1 : 0;
    }
    
    emit(this.bluePlayerId, {
        wins : (bluePeriodsWon > pinkPeriodsWon) ? 1 : 0,
        losses : (pinkPeriodsWon > bluePeriodsWon) ? 1 : 0,
        evens : (bluePeriodsWon == pinkPeriodsWon) ? 1 : 0,
        pointsScored : bluePointsScored,
        pointsAgainst : pinkPointsScored
    });
    emit(this.pinkPlayerId, {
        wins : (pinkPeriodsWon > bluePeriodsWon) ? 1 : 0,
        losses : (bluePeriodsWon > pinkPeriodsWon) ? 1 : 0,
        evens : (pinkPeriodsWon == bluePeriodsWon) ? 1 : 0,
        pointsScored : pinkPointsScored,
        pointsAgainst : bluePointsScored
    });
}
