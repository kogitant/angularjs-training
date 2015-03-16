describe('createAdController', function() {
    var $controller;
    var $rootScope;
    var apiUrl;
    var $httpBackend;
    var $location;

    beforeEach(module('front'));

    beforeEach(inject(function(_$controller_, _$rootScope_, _$httpBackend_, funmarketApiUrl, _$location_){
        $controller = _$controller_;
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
        apiUrl = funmarketApiUrl;
        $location = _$location_;
    }));

    it('Will publish an ad', function(){
        var scope = $rootScope.$new();
        var publishUrl = apiUrl + '/marketads';
        $httpBackend.when('POST', publishUrl).respond({});

        $location.path('/anotherroute'); //Set path to something else to verify the change

        $httpBackend.expectPOST(publishUrl);

        $controller('CreateAdCtrl', {$scope : scope});
        scope.item = {};
        scope.publish();

        $httpBackend.flush();

        expect($location.path()).toEqual('/');

    });

    it('Will upload an image', function(){
        var scope = $rootScope.$new();
        var expectedImageUrl = 'expectedImageUrl';
        var expectedThumbnailUrl = 'expectedThumbnailUrl';
        var uploadUrl = apiUrl + '/images';

        $httpBackend.when('POST', uploadUrl).respond({imageUrl : expectedImageUrl, thumbnailUrl : expectedThumbnailUrl});

        $httpBackend.expectPOST(uploadUrl);

        $controller('CreateAdCtrl', {$scope : scope});

        scope.upload([{}, {}]);

        $httpBackend.flush();

        expect(scope.item.imageUrl).toEqual(expectedImageUrl);
        expect(scope.item.thumbnailUrl).toEqual(expectedThumbnailUrl);

    });

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

});