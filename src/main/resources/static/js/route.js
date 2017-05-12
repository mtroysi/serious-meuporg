/**
 * Created by Morgane TROYSI on 26/04/2017.
 */

(function () {
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
            .state('app.home', {
                url: '/',
                templateUrl: 'js/home/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl'
            })
            .state('app.board-create', {
                url: '/board/create',
                templateUrl: 'js/board/board-create.html',
                controller: 'BoardController',
                controllerAs: 'ctrl'
            })
            .state('app.task-create', {
                url: '/task/create',
                templateUrl: 'js/task/task-create.html',
                controller: 'TaskController',
                controllerAs: 'ctrl'
            })
             .state('app.login', {
                url: '/login',
                templateUrl: 'js/login/login.html',
                controller: 'loginController',
                controllerAs: 'loginCtrl',
                data: {
                     requireLogin: true
                 }
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }
})();