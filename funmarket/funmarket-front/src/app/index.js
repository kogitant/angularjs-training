'use strict';

angular.module('front', ['ngResource', 'ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .when('/leaveadd', {

      })
      .when('adds', {
        templateUrl: 'app/'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
;
