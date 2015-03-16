describe('userDetailsService', function() {

    var $httpBackend, userDetailsService;

    var apiUrl = '/api/userdetails';
    var mockUserDetails = {username : 'markorautajoki', firstname : 'Marko', lastname : 'Rautajoki'};

    beforeEach(module('app'));

    beforeEach(inject(function(_$httpBackend_, _userDetailsService_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.when('GET', apiUrl).respond(mockUserDetails);
        userDetailsService = _userDetailsService_;
    }));

    it('Should fetch user details', function() {
        $httpBackend.expectGET(apiUrl);

        userDetailsService.get().$promise.then(function(result) {
            expect(result.username).toEqual(mockUserDetails.username);
            expect(result.firstname).toEqual(mockUserDetails.firstname);
            expect(result.lastname).toEqual(mockUserDetails.lastname);
        });

        $httpBackend.flush();

    });

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

});