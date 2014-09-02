'use strict';

// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'ngResource',
  'LocalStorageModule',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers',
  'luegg.directives'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/chatroom', {templateUrl: 'partials/chatroom.html', controller: 'ChatroomCtrl'});
  $routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'});
  $routeProvider.when('/logout', {template: '', controller: 'LogoutCtrl'});
	$routeProvider.otherwise({redirectTo: '/chatroom'});
}]).
run(function($rootScope, $location, SessionService) {
    $rootScope.$on( "$routeChangeStart", function(event, next, current) {
      	var author = SessionService.get('author');
      	if( !author ) {
        	if ( next.templateUrl != "partials/login.html" ) {
          		$location.path( "/login" );
        	}
      	}         
    });
});
