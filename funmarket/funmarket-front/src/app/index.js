'use strict';

angular.module('front', ['ngResource', 'ngRoute', 'angularFileUpload'])
  .value('funmarketApiUrl', 'http://localhost:8080') //funmarket-api.herokuapp.com')
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .when('/create', {
        templateUrl: 'app/create/create.html',
        controller: 'CreateAdCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
