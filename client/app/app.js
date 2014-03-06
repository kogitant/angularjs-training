'use strict';


// Create application module
var kikkeriApp = angular.module('kikkeriApp', ['ngResource', 'ngRoute', 'ngAnimate', 'kikkeriDirectives']);

//domain
kikkeriApp.constant('domain', 'http://localhost:8080');

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

    .otherwise({redirectTo: '/'});
});

