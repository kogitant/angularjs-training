'use strict';

angular.module('front', ['ngResource', 'ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .when('/leaveadd', {
        templateUrl: 'app/leaveadd/leaveadd.html',
        controller: 'LeaveAddCtrl'
      })
      .when('/adds', {
        templateUrl: 'app/adds/adds.html',
        controller: 'AddsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
