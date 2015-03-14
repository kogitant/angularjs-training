angular.module('myApp')
    .directive('hello', function factory() {
        return {
            template: '<div>Hi there <span ng-transclude><span></div>',
            transclude: true
        };
    });


