'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
  .controller('ChatroomCtrl', ['$scope', '$resource', '$timeout', function($scope, $resource, $timeout) {
  	  var apiRootUrl = "http://localhost:3000"; //http://funchat-api.herokuapp.com
	  var Message = $resource(apiRootUrl + '/messages/:messageId', {messageId:'@id'});

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
