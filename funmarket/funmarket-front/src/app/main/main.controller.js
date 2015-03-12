'use strict';

angular.module('front')
  .controller('MainCtrl', function ($scope, $location) {

    $scope.createNewAdd = function () {
      $location.path('/create');
    };

    $scope.item = {};

    $scope.adds = [];
  });
