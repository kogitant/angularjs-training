'use strict';

angular.module('front')
  .controller('MainCtrl', function ($scope, $location, MarketAdResource) {
    MarketAdResource.query().$promise.then(function (data) {
      $scope.marketAds = data;
    });

    $scope.item = {};

  });
