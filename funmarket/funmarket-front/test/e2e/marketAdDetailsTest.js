describe('markedAdDetails', function(){

    beforeEach(function(){
        browser.get('http://localhost:3000');
    });

    it('Should open the ad details page when ad is clicked in the frontpage', function(){
        element.all(by.repeater('ad in marketAds')).first().then(function(el) {
            //select first marketad
            el.element(by.css('h5 .ad-title')).getText().then(function(adTitle){
                //Find the ad title and click the link
                el.element(by.css('a')).click();
                //In the ad page verify that title is the same as the clicked ad title
                expect(element(by.css('.add-details h5')).getText()).toEqual(adTitle);
            });
        });
    });

});