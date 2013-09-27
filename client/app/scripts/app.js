'use strict';

var domain = "http://localhost:8080";


//INIT APPLICATION
var kikkeriApp = angular.module('kikkeriApp', ['ngResource', 'ngRoute', 'ngAnimate']);

//ROUTING
kikkeriApp.config(function ($routeProvider) {
  
  $routeProvider
    
    .when('/', {
      templateUrl: 'views/tournaments.html',
      controller: 'TournamentsCtrl'
    })
    
    .when('/tournament/:tournamentOid/', {
      templateUrl: 'views/tournament.html',
      controller: 'TournamentCtrl'
    })
    
    .when('/tournament/:tournamentOid/match', {
      templateUrl: 'views/match.html',
      controller: 'MatchCtrl'
    })

    .otherwise({redirectTo: '/'});
});


//REST SERVICES
kikkeriApp.factory('MatchesRsc', function($resource) {
  return $resource(domain + '/matches', {}, {
    get: {method: "GET", isArray: true},
    post: {method: "POST"},
  });
});

kikkeriApp.factory('TournamentsRsc', function($resource) {
  return $resource(domain + '/tournaments', {}, {
    get: {method: "GET", isArray: true},
    post: {method: "POST"}
  });
});

kikkeriApp.factory('TournamentStatisticsRsc', function($resource) {
  return $resource(domain + '/tournaments/:tournamentOid/statistics', {tournamentOid: '@tournamentOid'}, {
    get: {method: "GET", isArray: true},
  });
});

kikkeriApp.factory('TournamentRsc', function($resource) {
  return $resource(domain + '/tournaments/:tournamentOid', {tournamentOid: '@tournamentOid'}, {
    get: {method: "GET"},
  });
});