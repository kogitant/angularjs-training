'use strict';

angular.module('front')
  .controller('NavbarCtrl', function ($scope, $location) {
    $scope.createNewAdd = function () {
      $location.path('/create');
    };
  });
