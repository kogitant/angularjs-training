function (key, values) {
    var reduced = {
            wins : 0,
            losses : 0,
            evens : 0,
            pointsScored : 0,
            pointsAgainst : 0
    };
    for (var i = 0; i < values.length; i++) {
        reduced.wins += values[i].wins;
        reduced.losses += values[i].losses;
        reduced.evens += values[i].evens;
        reduced.pointsScored += values[i].pointsScored;
        reduced.pointsAgainst += values[i].pointsAgainst;
    }
    return reduced;
}
