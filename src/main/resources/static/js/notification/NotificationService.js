/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationService', function(NotificationWS) {
        var svc = {};

        svc.getNotificationByUser = function(id) {
            return UserWS.getNotificationByUser(id).then(function(response) {
                return response.data;
            });
        };

        return svc;
    })
})();