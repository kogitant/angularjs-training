var uuid = require('node-uuid');
var path = require('path');

describe('createAd', function(){

    var frontpage = 'http://localhost:3000/#/';

    beforeEach(function(){
        browser.get(frontpage + 'create');
    });

    function inputRequiredInfo(title, price) {
        element(by.model('item.title')).sendKeys(title);
        element(by.model('item.priceCents')).sendKeys(price);
        element(by.model('item.description')).sendKeys('kuvaus');
        element(by.model('item.phone')).sendKeys('123456');
        element(by.model('item.email')).sendKeys('asdadsds@ksdjkdsfjkdf.fi');
    }

    function publishButton() {
        return element(by.cssContainingText('button', 'Publish'));
    }

    it('Should contain the create new ad form', function(){
        expect(element(by.css('form[name="newAdForm"]')).isPresent()).toEqual(true);
    });

    it('Will disable the publish button in the beginning', function(){
        expect(element(by.cssContainingText('button', 'Publish')).isEnabled()).toEqual(false);
    });

    it('Will enable publish button when required fields are filled', function(){
        inputRequiredInfo('otsikko', 5);
        expect(publishButton().isEnabled()).toEqual(true);
    });

    it('Will publish the add', function(){
        var itemTitle = 'e2eTestItem' + uuid.v4();
        var price = 5;
        inputRequiredInfo(itemTitle, price);
        publishButton().click();
        expect(element(by.cssContainingText('h5', itemTitle)).isPresent()).toEqual(true);
    });

});