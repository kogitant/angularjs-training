describe('frontPage', function(){
    it('Should load the frontpage and show FUNMARKET title', function(){

        browser.get('http://localhost:3000');

        var h1 =  element(By.id('h1-title'));
        expect(h1.getText()).toEqual('FUNMARKET');
    });

    it('Should show a list of ads in the frontpage', function(){

        browser.get('http://localhost:3000');

        var ads = element.all(by.repeater('ad in marketAds'));
        expect(ads.count()).toBeGreaterThan(0);
    });
});
