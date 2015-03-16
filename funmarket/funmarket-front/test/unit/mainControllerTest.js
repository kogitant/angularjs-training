describe('mainController', function() {

    var $httpBackend;

    beforeEach(module('front'));

    beforeEach(inject(function(_$httpBackend_){
        $httpBackend = _$httpBackend_;
    }));

    it('Should fetch marketads', inject(function($controller, $rootScope, funmarketApiUrl){
        var queryUrl = funmarketApiUrl + '/marketads';
        var mockData = [{description: 'ad1'}, {description: 'ad2'}];
        var scope = $rootScope.$new();

        $httpBackend.when('GET', queryUrl).respond(mockData);
        $httpBackend.expectGET(queryUrl);
        $controller('MainCtrl', {$scope : scope});
        $httpBackend.flush();

        expect(scope.marketAds[0].description).toEqual('ad1');
        expect(scope.marketAds[1].description).toEqual('ad2');
    }));

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

});