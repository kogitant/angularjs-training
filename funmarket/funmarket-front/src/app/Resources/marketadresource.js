
angular.module('front')

.factory('MarketAdResource', function (funmarketApiUrl, $resource) {
  return $resource(funmarketApiUrl+ '/marketads');
});
