describe('userFilter', function() {
    var userFilter;

    var user1 = {name : 'Tapio', city  : 'Tampere'};
    var user2 = {name : 'Marko', city : 'Tampere'};
    var user3 = {name : 'Kari', city  : 'Helsinki'};
    var user4 = {name : 'Kalle', city : 'Turku'};

    var users = [user1, user2, user3, user4];

    beforeEach(module('app'));

    // Filters cannot be injected directly. They are part of $filter, which can be injected
    beforeEach(inject(function($filter){
        userFilter = $filter('userFilter');
    }));

    it('Will filter users by city (Tampere)', function() {
        expect(userFilter(users, 'Tampere')).toEqual([user1, user2]);
        expect(userFilter(users, 'Tampere')).toContain(user1);
        expect(userFilter(users, 'Tampere')).toContain(user2);

        //expect(userFilter(users, 'Tampere')).toContain(user1).toContain(user2);
    });

    it('Will filter users by city (Helsinki)', function() {
        expect(userFilter(users, 'Helsinki')).toEqual([user3]);
    });

    it('Will filter users by city (Turku)', function() {
        expect(userFilter(users, 'Turku')).toEqual([user4]);
    });

    it('Will return all users when no city provided', function() {
        expect(userFilter(users)).toEqual([user1, user2, user3, user4]);
    });
});
