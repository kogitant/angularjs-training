'use strict';
var kikkeriApp; // from app.js

kikkeriApp.directive('matchResult', function() {
	// Runs during compile
	return {
		// name: '',
		// priority: 1,
		// terminal: true,
		// scope: {}, // {} = isolate, true = child, false/undefined = no change
		// controller: function($scope, $element, $attrs, $transclude) {},
		// require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
		restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
		template: '<h1>{{match.bluePlayerName}}</h1>',
		// templateUrl: '../views/match-result.html',
		replace: true,
		// transclude: true,
		// compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
		link: function($scope, iElm, iAttrs, controller) {			
			$scope.match = iAttrs.match;
		}
	};
});
app.mod ule
// https://github.com/kseamon/fast-bind/blob/master/src/directives/bind-once/bind-once.js
app.directive('fastBindOnce', ['$parse', function($parse) {
	return {
		compile: function compile(element, attributes) {
			var expr = $parse(attributes.fastBindOnce);

			return function link(scope, element) {
				element.text(expr(scope));
			};
		}
	};
}]);
