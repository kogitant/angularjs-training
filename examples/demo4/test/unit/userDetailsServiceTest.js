describe('userDetailsService', function() {

    var $httpBackend, userDetailsService;

    var apiUrl = '/api/userdetails';
    var mockUserDetails = {username : 'akuankka', firstname : 'Aku', lastname : 'Ankka'};

    beforeEach(module('app'));

    beforeEach(inject(function(_$httpBackend_, _userDetailsService_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.when('GET', apiUrl).respond(mockUserDetails);
        userDetailsService = _userDetailsService_;
    }));

    it('Should fetch user details', function() {
        $httpBackend.expectGET(apiUrl);

        userDetailsService.getUserDetails().then(function(result) {
            var details = result.data;
            expect(details).toEqual(mockUserDetails);
        });

        $httpBackend.flush();

    });

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

});