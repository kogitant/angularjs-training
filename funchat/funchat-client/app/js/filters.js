'use strict';

/* Filters */

angular.module('myApp.filters', []).
  filter('interpolate', ['version', function(version) {
    return function(text) {
      return String(text).replace(/\%VERSION\%/mg, version);
    };
  }]).
  filter('slice', function() {
  	return function(arr, start, end) {
      if(!arr) {
        return arr;
      }
      return arr.slice(start, end);
  	};
  });
