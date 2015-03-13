'use strict';

angular.module('front')
  .controller('MainCtrl', function ($scope, $location, MarketAdResource) {

    $scope.createNewAdd = function () {
      $location.path('/create');
    };

    MarketAdResource.query().$promise.then(function (data) {
      $scope.marketAds = data;
    });

    $scope.item = {};

  });
