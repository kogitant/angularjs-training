describe('userDetailsService', function() {
    var apiUrl = '/api/userdetails';
    var mockUserDetails = {username : 'perttiesimerkki', firstname : 'Pertti', lastname : 'Esimerkki'};

    beforeEach(module('app'));

    it('Should fetch user details', inject(function($httpBackend, userDetailsResource) {
        $httpBackend.when('GET', apiUrl).respond(mockUserDetails);

        $httpBackend.expectGET(apiUrl);

        userDetailsResource.get().$promise.then(function(result) {
            expect(result.username).toEqual(mockUserDetails.username);
            expect(result.firstname).toEqual(mockUserDetails.firstname);
            expect(result.lastname).toEqual(mockUserDetails.lastname);
        });

        $httpBackend.flush();

        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();

    }));
});