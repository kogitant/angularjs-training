'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', [])
	.value('version', '0.1')
	.factory('SessionService', function (localStorageService) {
		return {
			set: function (key, value) {
				return localStorageService.set(key, value);
			},
			get: function (key) {
				return localStorageService.get(key);
			},
			clear: function () {
				localStorageService.clearAll();
			}		
    	};
	});

