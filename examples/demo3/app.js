angular.module('myApp', [])

.controller('myAppController', function ($scope) {
        $scope.awesomeModules = [
            {index: 1, presenter: 'Jaakko', description: 'SPA basics and Angular fundamentals'},
            {index: 2, presenter: 'Jaakko', description: 'Server communications, Promises and Router'},
            {index: 3, presenter: 'Tapio', description: 'Module configuration, Forms, $resource and directives'},
            {index: 4, presenter: 'Marko', description: 'Unit testing, Karma and E2E testing'}
        ];

        $scope.remove = function (index) {
            $scope.awesomeModules.splice(index, 1);
        };
    });