
angular.module('front')

.directive('marketAd', function () {
  return {
    templateUrl: 'components/marketad/marketad.html',
    restrict: 'E',
    scope: {
      data: '=data'
    }
  };
});
