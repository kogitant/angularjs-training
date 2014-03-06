'use strict';

// Configure domain
var domain = 'http://localhost:8080';

// Create application module
var kikkeriApp = angular.module('kikkeriApp', ['ngResource', 'ngRoute', 'ngAnimate', 'kikkeriDirectives']);

// Routing
kikkeriApp.config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      templateUrl: 'views/tournaments/tournaments.html',
      controller: 'TournamentsCtrl'
    })
    
    .when('/tournament/:tournamentOid/', {
      templateUrl: 'views/tournament/tournament.html',
      controller: 'TournamentCtrl'
    })
    
    .when('/tournament/:tournamentOid/match', {
      templateUrl: 'views/match/match.html',
      controller: 'MatchCtrl'
    })
    .otherwise({redirectTo: '/'});
});

