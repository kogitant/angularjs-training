'use strict';

describe('TournamentsCtrl: ', function(){

	var $scope, $location, $rootScope, createController;
	beforeEach(module('kikkeriApp'));

	beforeEach(inject(function ($injector) {

		$location = $injector.get('$location');
		$rootScope = $injector.get('$rootScope');
		$scope = $rootScope.$new();

		var $controller = $injector.get('$controller');

		createController = function () {
			return $controller('TournamentsCtrl', {
				'$scope': $scope
			});
		};
	}));

	it('true should equal true', function() {
		expect(true).toBe(true);
	});

	it('should be in root', function () {
			$location.path('/');
			expect($location.path()).toBe('/');
		}
	);

});