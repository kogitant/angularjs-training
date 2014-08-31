'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
  .constant('funChatApiUrl', 'http://funchat-api.herokuapp.com')
  .controller('ChatroomCtrl', ['$scope', '$resource', '$timeout', 'funChatApiUrl', function($scope, $resource, $timeout, funChatApiUrl) {
	  var Message = $resource(funChatApiUrl + '/messages/:messageId', {messageId:'@id'});

	  var tick = function () {
		  var messages = Message.query(function() {
		  	  $scope.messages = messages;
			  $timeout(tick, 1000);
		  });
      };
      tick();

	  $scope.send = function() {
	  	var message = $scope.input;
	  	Message.save(message, function(daa) {
	  		$scope.input.content = "";
	  		tick();
	  	});
	  };
  }])
  .controller('MyCtrl2', ['$scope', function($scope) {

  }]);
