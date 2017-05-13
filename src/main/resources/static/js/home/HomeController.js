/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('HomeController', function($location, $http, HomeService, CommonNotificationService) {
        var ctrl = this;

        ctrl.init = function() {
            CommonNotificationService.info('test', 'je suis adrien');
            HomeService.getResource().then(function(data) {
                ctrl.greeting = data;
            });
        };

        ctrl.init();
    })
})();