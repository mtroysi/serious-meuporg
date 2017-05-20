/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('NotificationController', function($location, $http, NotificationService, CommonNotificationService, AuthenticationService) {
        var ctrl = this;

        ctrl.init = function() {
            // The user has just seen all notifications
            NotificationService.readAllNotification(AuthenticationService.getUserId());
        };

        ctrl.init();
    })
})();