describe('userFilter', function() {
    var userFilter;

    var user1 = {name : 'Tapio', city  : 'Tampere'};
    var user2 = {name : 'Marko', city : 'Tampere'};
    var user3 = {name : 'Kari', city  : 'Helsinki'};
    var user4 = {name : 'Kalle', city : 'Turku'};

    var users = [user1, user2, user3, user4];

    beforeEach(module('app'));

    beforeEach(inject(function($filter){
        userFilter = $filter('userFilter');
    }));

    it('Will filter users by city (Tampere)', function() {
        expect(userFilter(users, 'Tampere')).toEqual([user1, user2]);
    });

    it('Will filter users by city (Helsinki)', function() {
        expect(userFilter(users, 'Helsinki')).toEqual([user3]);
    });

    it('Will filter users by city (Turku)', function() {
        expect(userFilter(users, 'Turku')).toEqual([user4]);
    });
});