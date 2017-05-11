'use strict';

var path = require('path');
var gulp = require('gulp');
var less = require('gulp-less');
var cssmin = require('gulp-cssmin');
var concat = require('gulp-concat');
var conf = require('./conf');

var $ = require('gulp-load-plugins')();

var wiredep = require('wiredep').stream;
var _ = require('lodash');

var browserSync = require('browser-sync');

gulp.task('inject-reload', ['inject'], function() {
  browserSync.reload();
});

gulp.task('inject', ['scripts'], function () {
  var injectStyles = gulp.src([
    path.join(conf.paths.src, '/**/*.css')
  ], { read: false });

  var injectLess = gulp.src([
    path.join(conf.paths.src, '/less/**/*.less')
    ])
    .pipe(less())
    .pipe(cssmin())
    .pipe(concat('meuporg.css'))
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve/less')));



  var injectScripts = gulp.src([
    path.join(conf.paths.src, '*.module.js'),
    path.join(conf.paths.src, 'js/**/*.js'),
    path.join('!' + conf.paths.src, '*.spec.js')
  ])
  .pipe($.angularFilesort()).on('error', conf.errorHandler('AngularFilesort'));

  var injectOptions = {
    ignorePath: [conf.paths.src, path.join(conf.paths.tmp, '/serve')],
    addRootSlash: false
  };

  return gulp.src(path.join(conf.paths.src, '/*.html'))
    .pipe($.inject(injectStyles, injectOptions))
    .pipe($.inject(injectLess, injectOptions))
    .pipe($.inject(injectScripts, injectOptions))
    .pipe(wiredep(_.extend({}, conf.wiredep)))
    .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve')));
});
