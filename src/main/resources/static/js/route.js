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
            .state('appSimple.home', {
                url: '/',
                templateUrl: 'js/home/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl'
            })
            .state('app.dashboard', {
                url: '/dashboard/',
                templateUrl: 'js/dashboard/dashboard.html',
                controller: 'DashboardController',
                controllerAs: 'ctrl'
            })
            .state('app.board-create', {
                url: '/board/create',
                templateUrl: 'js/board/board-create.html',
                controller: 'BoardController',
                controllerAs: 'ctrl'
            })
            .state('app.board-preview', {
                url: '/board/preview/{id}',
                templateUrl: 'js/board/preview/board-preview.html',
                controller: 'BoardPreviewController',
                controllerAs: 'ctrl'
            })
            .state('app.task-create', {
                url: '/task/create',
                templateUrl: 'js/task/create/task-create.html',
                controller: 'TaskCreateController',
                controllerAs: 'ctrl'
            })
            .state('app.task-show', {
                url: '/task/{id}',
                templateUrl: 'js/task/show/task-show.html',
                controller: 'TaskShowController',
                controllerAs: 'ctrl'
            })
            .state('app.task-update', {
                url: '/task/update/{id}',
                templateUrl: 'js/task/update/task-update.html',
                controller: 'TaskEditController',
                controllerAs: 'ctrl'
            })
            .state('app.tag-create', {
                url: '/tag/create',
                templateUrl: 'js/tag/create/tag-create.html',
                controller: 'TagCreateController',
                controllerAs: 'ctrl'
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }
})();