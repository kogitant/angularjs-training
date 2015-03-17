describe('User list ', function(){
    beforeEach(function(){
        browser.get('http://localhost:3000');
    });

    it('Will show 4 users when no filter is selected', function(){
        expect(element.all(by.repeater('user in users')).count()).toEqual(4);
    });

    it('Will show 2 users when Tampere filter is selected', function() {
        element(by.cssContainingText('option', 'Tampere')).click();
        expect(element.all(by.repeater('user in users')).count()).toEqual(2);
    });

    it('Will select user from list when arrow is clicked', function() {
        var userToSelect = 'Tapio';
        element.all(by.repeater('user in users')).filter(function(elem){
            return elem.element(by.binding('user.name')).getText().then(function(text) {
               return text === userToSelect;
            });
        }).then(function(filteredElements) {
            filteredElements[0].element(by.css('a')).click();
            expect(element(by.binding('selectedUser.name')).getText()).toEqual(userToSelect);
        });
    })
});