'use strict';


describe('mainController', function() {

    var httpBackend;

    var bundle = {
        "CREATE_NEW_ADD" : "Luo uusi ilmoitus",
        "SEARCH_WORD" : "hakusana"
    };
    var messageBundleUrl = 'locale/locale-fi.json';

    var adsUrl = undefined;
    var ads = [];

    beforeEach(
        //Module dependencies and injections
        // Import module, not a specific component from a module
        module('front')
    )

    beforeEach(inject(function(_$httpBackend_, _funmarketApiUrl_){
        httpBackend = _$httpBackend_;
        httpBackend.when('GET', messageBundleUrl).respond(bundle);

        adsUrl = _funmarketApiUrl_ + '/marketads';


    }));

    it('Should not die if no marketads returned',  inject(function($controller, $rootScope){
       mockAndExpectHttpRequests(ads);

        // create the blank scope for the controller
        var scope = $rootScope.$new();

        var mainCtrl = $controller('MainCtrl', {$scope: scope});
        expect(mainCtrl).toBeDefined();


       httpBackend.flush();

        expect(scope.marketAds).toBeDefined();
        expect(scope.marketAds.length).toEqual(0);

    }));


    it('Should have returned ads in scope',  inject(function($controller, $rootScope){
        ads = [{"id":"55072b26e4b0769e6e64728c","title":"Kalman koura","description":"Kalman koura","priceCents":99,"imageUrl":"http://funmarket-api.herokuapp.com/images/55072b1de4b0769e6e647288","thumbnailUrl":"http://funmarket-api.herokuapp.com/images/55072b1de4b0769e6e64728a","email":"","phone":"0403402034"},{"id":"550aa3a1e4b0a73ae2e933cc","title":"sadfgf","description":"sfsd","priceCents":232,"imageUrl":null,"thumbnailUrl":null,"email":"3dsafdsf@sdgfdf.com","phone":"233"},{"id":"550aaaa1e4b0a73ae2e933cd","title":"dgfdg","description":"xcfzfdas","priceCents":33,"imageUrl":null,"thumbnailUrl":null,"email":"d@d.fi","phone":"2143234241"}];

        mockAndExpectHttpRequests(ads);

        // create the blank scope for the controller
        var scope = $rootScope.$new();

        var mainCtrl = $controller('MainCtrl', {$scope: scope});
        expect(mainCtrl).toBeDefined();


       httpBackend.flush();

        expect(scope.marketAds).toBeDefined();
        expect(scope.marketAds.length).toEqual(3);

    }));

    afterEach(function() {
        //Something to do after each test?
    });

    function mockAndExpectHttpRequests(adsToReturn) {
        httpBackend.when('GET', adsUrl).respond(adsToReturn);

        httpBackend.expectGET(messageBundleUrl);
        httpBackend.expectGET(adsUrl);
    }

});
