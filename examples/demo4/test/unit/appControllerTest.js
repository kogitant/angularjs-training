describe('appController', function() {
    var $controller, $rootScope, $q;

    beforeEach(module('app'));

    var userDetails = {
        username : 'markorautajoki',
        firstname : 'Marko',
        lastname : 'Rautajoki'
    };

    var user1 = {name : 'Tapio', city  : 'Tampere'};
    var user2 = {name : 'Marko', city : 'Tampere'};
    var user3 = {name : 'Kari', city  : 'Helsinki'};
    var user4 = {name : 'Kalle', city : 'Turku'};

    var users = [user1, user2, user3, user4];

    var userDetailsServiceMock = {
        getUserDetails : function(){
            var deferred = $q.defer();
            deferred.resolve({data: userDetails});
            return deferred.promise;
        }
    }

    var userServiceMock = {
        getUsers : function() {
            var deferred = $q.defer();
            deferred.resolve({data: users});
            return deferred.promise;
        }
    }

    beforeEach(inject(function(_$controller_, _$rootScope_, _$q_){
        $controller = _$controller_;
        $rootScope = _$rootScope_;
        $q = _$q_;
    }));

    it('Will fetch user details and users list', function() {
        var scope = $rootScope.$new();
        $controller('appController', {$scope : scope, userDetailsService : userDetailsServiceMock, userService : userServiceMock});
        scope.$digest();
        expect(scope.userDetails).toEqual(userDetails);
        expect(scope.users).toEqual(users);
    });

});