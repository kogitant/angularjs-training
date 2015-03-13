angular.module('app', []);

angular.module('app').controller('appController', function($scope, userDetailsService, userService) {
    userDetailsService.getUserDetails().then(function(result) {
       $scope.userDetails = result.data;
    });
    userService.getUsers().then(function(result){
        $scope.users = result.data;
    });
});

angular.module('app').service('userDetailsService', function($http) {
    var userDetails = $http.get('/api/userdetails');
    this.getUserDetails = function() {
        return userDetails;
    }
});

angular.module('app').service('userService', function($http) {
    var users = $http.get('/api/users');
    this.getUsers = function() {
        return users;
    }
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




