'use strict';

angular.module('front')
  .factory('MarketAdResource', function (funmarketApiUrl, $resource) {
    return $resource(funmarketApiUrl + '/marketads/:id');
  })
  .config(function ($routeProvider) {
    $routeProvider
      .when('/marketads/:id', {
        templateUrl: 'app/marketad/marketad.html',
        controller: 'ViewMarketAdCtrl',
        resolve: {marketAd: function(MarketAdResource, $route) {
          return MarketAdResource.get({id: $route.current.params.id})
        }}
      });
  })
  .controller('ViewMarketAdCtrl', function ($scope, marketAd) {
    $scope.marketAd = marketAd;
  });
