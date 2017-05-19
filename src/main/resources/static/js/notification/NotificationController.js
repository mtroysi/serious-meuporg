/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('NotificationController', function($location, $http, NotificationService, CommonNotificationService) {
        var ctrl = this;

        ctrl.init = function() {

        };

        ctrl.init();
    })
})();