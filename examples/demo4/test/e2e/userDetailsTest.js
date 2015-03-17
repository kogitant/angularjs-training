describe('User details ', function(){
    beforeEach(function(){
        browser.get('http://localhost:3000');
    });

    it('Will show user details', function(){
        expect(element(by.binding('userDetails.username')).getText()).toEqual('markorautajoki');
        expect(element(by.binding('userDetails.firstname')).getText()).toEqual('Marko');
        expect(element(by.binding('userDetails.lastname')).getText()).toEqual('Rautajoki');
    });

});