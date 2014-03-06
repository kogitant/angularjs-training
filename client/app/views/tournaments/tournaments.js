'use strict';
var kikkeriApp; // from app.js

kikkeriApp.factory('TournamentsModel', ['TournamentsRsc', function(TournamentsRsc) {
	var model = {
		tournaments: [],
		refresh: function () {
			TournamentsRsc.get(function(result) {
				model.tournaments = result;
			}, function(error) {
				console.log(error);
			});
		}
	};
	return model;
}]);

kikkeriApp.controller('TournamentsCtrl', ['$scope', '$location', 'TournamentsModel', 'TournamentsRsc', function($scope, $location, TournamentsModel, TournamentsRsc) {
	$scope.tournamentsModel = TournamentsModel;
	$scope.tournamentsModel.refresh();

	$scope.tournamentInformation = function(tournamentId) {
		$location.path('/tournament/' + tournamentId);
	};

	$scope.createNewTournament = function(tournamentName) {
		var tournament = {
			configurations: {
				SERIES: {
					periods: 2,
					pointsForWin: 2,
					pointsForLoss: 0,
					pointsForEven: 1
				}
			},
			name: tournamentName
		};

		TournamentsRsc.post(tournament, function(result) {
			$scope.tournamentsModel.refresh();
		}, function(error) {
			console.log(error);
		});
	};
}]);

