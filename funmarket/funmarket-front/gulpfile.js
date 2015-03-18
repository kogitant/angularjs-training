'use strict';

var gulp = require('gulp');

gulp.paths = {
  src: 'src',
  dist: 'dist',
  tmp: '.tmp',
  e2e: 'test/e2e_exercise',
  unit : 'test/unit_exercise'
};

require('require-dir')('./gulp');

gulp.task('default', ['clean'], function () {
    gulp.start('build');
});
