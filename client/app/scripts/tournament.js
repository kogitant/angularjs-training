'use strict';

kikkeriApp.factory('TournamentModel', function(MatchesRsc, TournamentStatisticsRsc, TournamentRsc) {
	var model = new function() {
		this.tournament = {};
		this.matches = [];
		this.statistics = []; 


		this.refresh = function(tournamentOid) {
			
			TournamentRsc.get({tournamentOid: tournamentOid}, function(result) {
				model.tournament = result;
			});

			MatchesRsc.get(function(result) {
				model.matches = result;
			}, function(error) {	
				console.log(error);
			});

			TournamentStatisticsRsc.get({tournamentOid: tournamentOid}, function(result) {
				model.statistics = result;
			}, function(error) {
				console.log(error);
			});
		}
	} 

	return model;
});


function TournamentCtrl($rootScope, $scope, $routeParams, TournamentModel) {
	$scope.tournamentModel = TournamentModel;
	$scope.tournamentModel.refresh($routeParams.tournamentOid);

	$scope.showLeader = function() {
		$scope.showPlayer = !$scope.showPlayer;
		$rootScope.$broadcast('centralizeElement');
	}
}