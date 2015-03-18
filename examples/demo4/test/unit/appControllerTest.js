describe('appController', function() {
    var $controller, $rootScope, $q;

    var userDetails = {
        username : 'perttiesimerkki',
        firstname : 'Pertti',
        lastname : 'Esimerkki'
    };

    var user1 = {name : 'Tapio', city  : 'Tampere'};
    var user2 = {name : 'Marko', city : 'Tampere'};
    var user3 = {name : 'Kari', city  : 'Helsinki'};
    var user4 = {name : 'Kalle', city : 'Turku'};

    var users = [user1, user2, user3, user4];

    var userDetailsResourceMock = {
        get : function() {
            return userDetails;
        }
    };

    var userResourceMock = {
        query : function() {
            return users;
        }
    };

    beforeEach(module('app'));

    beforeEach(inject(function(_$controller_, _$rootScope_, _$q_){
        $controller = _$controller_;
        $rootScope = _$rootScope_;
        $q = _$q_;
    }));

    it('Will fetch user details and users list', function() {
        var scope = $rootScope.$new();
        $controller('appController', {$scope : scope, userDetailsResource : userDetailsResourceMock, userResource : userResourceMock});
        expect(scope.userDetails).toEqual(userDetails);
        expect(scope.users).toEqual(users);
    });

});