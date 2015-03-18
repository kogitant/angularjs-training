describe('appController', function() {
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

    beforeEach(module('app'));

    it('Will fetch user details and users list', inject(function($controller, $rootScope, $httpBackend) {
        var userDetailsUrl = '/api/userdetails';
        var usersUrl = '/api/users';

        var scope = $rootScope.$new();
        $httpBackend.when('GET', userDetailsUrl).respond(userDetails);
        $httpBackend.when('GET', usersUrl).respond(users);

        $httpBackend.expectGET(userDetailsUrl);
        $httpBackend.expectGET(usersUrl);

        $controller('appController', {$scope : scope});

        $httpBackend.flush();

        expect(scope.userDetails.username).toEqual(userDetails.username);
        expect(scope.users.length).toEqual(users.length);

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    }));

});