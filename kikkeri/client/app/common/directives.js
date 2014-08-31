'use strict';

var kikkeriDirectives = angular.module('kikkeriDirectives', []);

// https://github.com/kseamon/fast-bind/blob/master/src/directives/bind-once/bind-once.js
kikkeriDirectives.directive('fastBindOnce', ['$parse', function($parse) {
	return {
		compile: function compile(element, attributes) {
			var expr = $parse(attributes.fastBindOnce);

			return function link(scope, element) {
				element.text(expr(scope));
			};
		}
	};
}]);

kikkeriDirectives.directive('centralize', function() {
	return {
		restrict: 'A',
		link: function($scope, element, attrs) {

			var elemWidth = element.context.offsetWidth;
			var elemHeight = element.context.offsetHeight;
			var top = Math.max(0, (($(window).height() - elemHeight) / 2) + $(window).scrollTop());
			var left = Math.max(0, (($(window).width() - elemWidth) / 2) + $(window).scrollLeft());
			$(element).css({
				position: 'absolute',
				'z-index': 1000,
				margin:0,
				top: (top > 0 ? top : 0)+'px',
				left: (left > 0 ? left : 0)+'px'
			});

		}
	}
});