/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function () {
    'use strict';

    var helloApp = angular.module('hello', ['ui.router']);

    /** @ngInject */
    helloApp.controller('HomeController', function ($location, $http, HomeService) {
        var ctrl = this;

        ctrl.init = function () {
            HomeService.getResource().then(function (data) {
                ctrl.greeting = data;
                console.log();
            });
        };

        ctrl.init();
    })
})();
