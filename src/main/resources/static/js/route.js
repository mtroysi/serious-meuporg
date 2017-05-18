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
                templateUrl: 'layout/full.html',
                data: {
                    requireLogin: true
                }
            })
            .state('appSimple', {
                abstract: true,
                templateUrl: 'layout/simple.html',
                data: {
                    requireLogin: false
                }
            })
            .state('appSimple.home', {
                url: '/',
                templateUrl: 'js/home/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl'
            })
            .state('appSimple.login', {
                url: '/login',
                templateUrl: 'js/user/login/login.html',
                controller: 'LoginController',
                controllerAs: 'loginCtrl'
            })
            .state('appSimple.signup', {
                url: '/signup',
                templateUrl: 'js/user/signup/signup.html',
                controller: 'SignupController',
                controllerAs: 'signupCtrl'
            })
             .state('app.signin', {
                url: '/signin',
                controller: 'SigninController'
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
            .state('app.board-edit', {
                url: '/board/edit/{id}',
                templateUrl: 'js/board/board-edit.html',
                controller: 'BoardController',
                controllerAs: 'ctrl'
            })
            .state('app.board-preview', {
                url: '/board/preview/{id}',
                templateUrl: 'js/board/preview/board-preview.html',
                controller: 'BoardPreviewController',
                controllerAs: 'ctrl'
            })
            .state('app.board-preview-common', {
                url: '/board/preview-common',
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
                controller: 'TaskUpdateController',
                controllerAs: 'ctrl'
            })
            .state('app.tag-create', {
                url: '/tag/create',
                templateUrl: 'js/tag/create/tag-create.html',
                controller: 'TagCreateController',
                controllerAs: 'ctrl'
            })
            .state('app.tag-update', {
                url: '/tag/update/{id}',
                templateUrl: 'js/tag/update/tag-update.html',
                controller: 'TagUpdateController',
                controllerAs: 'ctrl'
            })
 	   .state('app.notification', {
                url: '/notification',
                templateUrl: 'js/notification/notification.html',
                controller: 'NotificationController',
                controllerAs: 'ctrl'
            })
            .state('app.bidValidation', {
                url: '/bid/{idBoard}/validation',
                templateUrl: 'js/bid/validation/bid.validation.html',
                controller: 'BidValidationController',
                controllerAs: 'ctrl'
            })
            .state('app.bidPreview', {
                url: '/bid/{idBoard}',
                templateUrl: 'js/bid/preview/bid.preview.html',
                controller: 'BidPreviewController',
                controllerAs: 'ctrl'
            })
	    .state('app.shop', {
                url: '/shop',
                templateUrl: 'js/shop/shop.html',
                controller: 'ShopController',
                controllerAs: 'ctrl'
            }).state('app.comment', {
                url: '/comment/{id}',
                templateUrl: 'js/comment/show/comment-show.html',
                controller: 'CommentController',
                controllerAs: 'ctrl'
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }
})();