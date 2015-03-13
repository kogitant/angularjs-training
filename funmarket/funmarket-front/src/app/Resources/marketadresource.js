
angular.module('front')

.factory('MarketAdResource', function ($resource) {
  return $resource('http://funmarket-api.herokuapp.com/marketads');
});
