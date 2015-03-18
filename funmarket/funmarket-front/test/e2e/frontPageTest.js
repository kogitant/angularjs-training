describe('frontPage', function(){

    beforeEach(function(){
        browser.get('http://localhost:3000');
    });

    it('Should load the frontpage and show FUNMARKET title', function(){
        expect(element(by.cssContainingText('h1', 'FUNMARKET')).isPresent()).toEqual(true);
    });

    it('Should show a list of ads in the frontpage', function(){
        expect(element.all(by.repeater('ad in marketAds')).count()).toBeGreaterThan(0);
    });
});