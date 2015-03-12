
angular.module('front')

.directive('add', function () {
  return {
    templateUrl: 'components/add/add.html',
    restrict: 'E',
    scope: {
      data: '=data'
    }
  }
});
