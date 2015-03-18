describe('User details ', function(){
    beforeEach(function(){
        browser.get('http://localhost:3000');
    });

    it('Will show user details', function(){
        expect(element(by.binding('userDetails.username')).getText()).toEqual('perttiesimerkki');
        expect(element(by.binding('userDetails.firstname')).getText()).toEqual('Pertti');
        expect(element(by.binding('userDetails.lastname')).getText()).toEqual('Esimerkki');
    });

});