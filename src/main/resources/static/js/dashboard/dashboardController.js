/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('DashboardController', function($location, $http, CommonNotificationService) {
        var ctrl = this;

        ctrl.init = function() {
            CommonNotificationService.info('Bienvenue', 'je suis sur le dashboard');
        };

        ctrl.init();
    })
})();