'use strict';

module.exports = function(config) {

    config.set({
        autoWatch : true,

        frameworks: ['jasmine'],

        browsers : ['Chrome'],

        files: [
            'public/lib/angular.js',
            'public/lib/*.js',
            'public/**/*.js',
            'test/unit/lib/*.js',
            'test/unit/**/*.js'
        ],

        singleRun : false
    });
};
