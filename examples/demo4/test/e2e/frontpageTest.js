describe('frontpage', function(){
    it('should load the front page', function(){
        browser.get('http://localhost:3000');
        expect(element(by.cssContainingText('h1', 'Page title')).isPresent()).toEqual(true);

        //browser.sleep(10000);
    });
});
