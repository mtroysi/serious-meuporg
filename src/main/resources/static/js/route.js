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
            .state('app.profil', {
                url: '/profil/{idUser}',
                templateUrl: 'js/user/profil/profil.html',
                controller: 'ProfilController',
                controllerAs: 'ctrl'
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
                url: '/board/edit/{idBoard}',
                templateUrl: 'js/board/board-edit.html',
                controller: 'BoardController',
                controllerAs: 'ctrl'
            })
            .state('app.board-preview', {
                url: '/board/preview/{idBoard}?idtask',
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
            })
            .state('app.shopCreate', {
                url: '/shop-create',
                templateUrl: 'js/shop/create/shop-create.html',
                controller: 'ShopCreateController',
                controllerAs: 'ctrl'
            })
            .state('app.inventory', {
                url: '/inventory',
                templateUrl: 'js/inventory/inventory.html',
                controller: 'InventoryController',
                controllerAs: 'ctrl'
            });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }
})();