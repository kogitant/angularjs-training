'use strict';
var kikkeriApp; // from app.js

kikkeriApp.factory('TournamentModel',
	['MatchesRsc', 'TournamentStatisticsRsc', 'TournamentRsc',
	function(MatchesRsc, TournamentStatisticsRsc, TournamentRsc) {

	var model = {
		tournament: {},
		matches: [],
		statistics: [],
		refresh: function(tournamentOid) {		
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
	};
	return model;
}]);


kikkeriApp.controller('TournamentCtrl', ['$rootScope', '$scope', '$routeParams', 'TournamentModel', function($rootScope, $scope, $routeParams, TournamentModel) {
	$scope.tournamentModel = TournamentModel;
	$scope.tournamentModel.refresh($routeParams.tournamentOid);

	$scope.showLeader = function() {
		$scope.showPlayer = !$scope.showPlayer;
		$rootScope.$broadcast('centralizeElement');
	};
}]);