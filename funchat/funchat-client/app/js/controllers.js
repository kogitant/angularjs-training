'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
	.constant('funChatApiUrl', 'http://funchat-api.herokuapp.com')
	.controller('ChatroomCtrl', ['$scope', '$resource', '$timeout', '$location', 'funChatApiUrl', 'SessionService',
		                        function($scope, $resource, $timeout, $location, funChatApiUrl, SessionService) {
	  	var Message = $resource(funChatApiUrl + '/messages/:messageId', {messageId:'@id'});
	  	$scope.input = {};
	  	$scope.input.author = SessionService.get('author');

	  	var tick = function () {
		  	var messages = Message.query(function() {
		  	  	$scope.messages = messages;
		  	  	$timeout(tick, 1000);
		  	});
      	};
      	tick();

	  	$scope.send = function () {
	  		var message = $scope.input;
	  		Message.save(message, function(daa) {
	  			$scope.input.content = "";
	  			tick();
	  		});
	  	};
	}])
	.controller('LoginCtrl', ['$scope', '$location', 'SessionService', function($scope, $location, SessionService) {
		$scope.login = function () {
			SessionService.set('author', $scope.nickname);
			$location.path("/");
		};
	}])
	.controller('LogoutCtrl', ['$scope', '$location', 'SessionService', function($scope, $location, SessionService) {
		SessionService.clear();
		$location.path("/");
	}]);
