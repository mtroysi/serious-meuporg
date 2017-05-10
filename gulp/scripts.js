'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var browserSync = require('browser-sync');

var $ = require('gulp-load-plugins')();


gulp.task('scripts-reload', function() {
  return buildScripts()
    .pipe(browserSync.stream());
});

gulp.task('scripts', function() {
  return buildScripts();
});

function buildScripts() {
  return gulp.src(path.join(conf.paths.src, '!(bullet*|i18n*|*.e2e|*.html|*.spec|*.mock).js'))
    .pipe($.jshint(path.join(conf.paths.src, '.jshintrc')))
    .pipe($.jshint.reporter('default'))
}
