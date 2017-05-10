/**
 * Created by Morgane TROYSI on 26/04/2017.
 */

(function () {
    'use strict';

    angular.module('hello').config(routerConfig);

    /** @ngInject */
    function routerConfig($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider.state('home', {
            url: '/',
            templateUrl: 'js/home/home.html',
            controller: 'HomeController',
            controllerAs: 'ctrl'
        });

        $urlRouterProvider.otherwise('/');
        $httpProvider.defaults.cache = false;

    }})();