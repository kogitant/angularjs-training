'use strict';


kikkeriApp.factory('MatchModel', function($q, MatchesRsc, TournamentModel) {
	var model = new function() {


		this.match = {
			type: "SERIES",
			periods: [{},{}]
		}

		this.persistMatch = function(tournamentId) {
			var deferred = $q.defer();
			model.match.tournamentId = tournamentId;

			MatchesRsc.post(model.match, function() {
				deferred.resolve();
			});

			return deferred.promise;
		}
	}
	return model;
});

function MatchCtrl($scope, $routeParams, $location, MatchModel, TournamentModel) {
	$scope.tournamentId = $routeParams.tournamentOid;
	$scope.tournamentModel = TournamentModel;
	$scope.matchModel = MatchModel;

	$scope.submit = function() {

		var promise = $scope.matchModel.persistMatch($scope.tournamentId);
		promise.then(function() {
			$location.path('/tournament/' + $scope.tournamentId);
		});
	}

}
