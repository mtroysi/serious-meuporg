/**
 * Created by Morgane TROYSI on 26/04/2017.
 */

(function() {
    'use strict';

    angular.module('hello').config(routerConfig);

    /** @ngInject */
    function routerConfig($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('app', {
                abstract: true,
                templateUrl: 'layout/full.html'
            })
            .state('appSimple', {
                abstract: true,
                templateUrl: 'layout/simple.html'
            })
            .state('appSimple.home', {
                url: '/',
                templateUrl: 'js/home/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: false
                }
            })
            .state('appSimple.login', {
                url: '/login',
                templateUrl: 'js/login/login.html',
                controller: 'LoginController',
                controllerAs: 'loginCtrl',
                data: {
                    requireLogin: false
                }
            })
            .state('appSimple.signup', {
                url: '/signup',
                templateUrl: 'js/signup/signup.html',
                controller: 'SignupController',
                controllerAs: 'signupCtrl',
                data: {
                    requireLogin: false
                }
            })
            .state('app.dashboard', {
                url: '/dashboard/',
                templateUrl: 'js/dashboard/dashboard.html',
                controller: 'DashboardController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: true
                }
            })
            .state('app.board-create', {
                url: '/board/create',
                templateUrl: 'js/board/board-create.html',
                controller: 'BoardController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: true
                }
            })
            .state('app.board-preview', {
                url: '/board/preview/{id}',
                templateUrl: 'js/board/preview/board-preview.html',
                controller: 'BoardPreviewController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: true
                }
            })
            .state('app.task-create', {
                url: '/task/create',
                templateUrl: 'js/task/task-create.html',
                controller: 'TaskController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: true
                }
            })
            .state('app.task-show', {
                url: '/task/{id}',
                templateUrl: 'js/task/show/task-show.html',
                controller: 'TaskShowController',
                controllerAs: 'ctrl',
                data: {
                    requireLogin: true
                }
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }
})();