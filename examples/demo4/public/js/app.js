angular.module('app', ['ngResource']);

angular.module('app').controller('appController', function($scope, userDetailsResource, userResource) {

    $scope.userDetails = userDetailsResource.get();
    $scope.users = userResource.query();

    $scope.selectedUser = null;
    $scope.selectUser = function(user) {
        $scope.selectedUser = user;
    }
});

angular.module('app').factory('userDetailsResource', function($resource) {
    return $resource('/api/userdetails');
});

angular.module('app').factory('userResource', function($resource) {
    return $resource('/api/users');
});

angular.module('app').filter('userFilter', function($filter) {
    return function(users, city){
        if(!city) {
            return users;
        } else {
            return $filter('filter')(users, {city : city});
        }
    }
});




