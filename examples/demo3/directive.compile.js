angular.module('myApp')

.directive('crazyManipulations', function () {
        return {
            compile: function (elem) {
                $(elem).prepend('<h1>I've been added in compile-phase</h1>');
            },
            restrict: 'A'
        };
    });