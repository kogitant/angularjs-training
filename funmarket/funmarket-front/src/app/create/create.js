'use strict';

angular.module('front')
  .controller('CreateAdCtrl', function ($scope, $location, MarketAdResource, $http, $upload, funmarketApiUrl) {
    $scope.item = {};

    $scope.return = function () {
      $scope.item = {};
      $location.path('/');
    };

    $scope.preview = function () {
      $scope.toggleReview = !$scope.toggleReview;
    };

    $scope.publish = function () {
      MarketAdResource.save($scope.item).$promise.then(function () {
        $location.path('/');
      });
    };

    $scope.upload = function (files) {
      if (files && files.length) {
        for (var i = 0; i < files.length; i++) {
          var file = files[i];
          $upload.upload({
            url: funmarketApiUrl + '/images',
            //fields: {},
            file: file
          }).progress(function (evt) {
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
          }).success(function (data, status, headers, config) {
            $scope.item.imageUrl = data.url;
          });
        }
      }
    };
});
