
angular.module('front')

.controller('CreateAddCtrl', function ($scope, $location) {

    $scope.return = function () {
      $scope.item = {};
      $location.path('/');
    };

    $scope.review = function () {

    };

    $scope.publish = function () {
      console.log($scope.item);
    };
});
