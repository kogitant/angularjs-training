
'use strict';

// Rest services
kikkeriApp.factory('MatchesRsc', function($resource) {
	return $resource(domain + '/matches', {}, {
		get: {method: 'GET', isArray: true},
		post: {method: 'POST'}
	});
});

kikkeriApp.factory('TournamentsRsc', function($resource) {
	return $resource(domain + '/tournaments', {}, {
		get: {method: 'GET', isArray: true},
		post: {method: 'POST'}
	});
});

kikkeriApp.factory('TournamentStatisticsRsc', function($resource) {
	return $resource(domain + '/tournaments/:tournamentOid/statistics', {tournamentOid: '@tournamentOid'}, {
		get: {method: 'GET', isArray: true}
	});
});

kikkeriApp.factory('TournamentRsc', function($resource) {
	return $resource(domain + '/tournaments/:tournamentOid', {tournamentOid: '@tournamentOid'}, {
		get: {method: 'GET'}
	});
});
