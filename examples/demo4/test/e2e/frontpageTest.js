describe('frontpage', function(){

    it('should load the front page', function(){
        browser.get('http://localhost:3000');
        expect(element(by.cssContainingText('h2', 'User info')).isPresent()).toEqual(true);
    });

});