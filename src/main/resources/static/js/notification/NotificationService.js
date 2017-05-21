/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationService', function(NotificationWS, $injector) {
        var svc = {};

        svc.getNotificationByUser = function(id) {
            return NotificationWS.getNotificationByUser(id).then(function(response) {
                return response.data;
            });
        };

        svc.createNotification = function(notification) {
            return NotificationWS.createNotification(notification).then(function(response) {
                return response.data;
            });
        };

        svc.readAllNotification = function(idUser) {
            $injector.get('CommonNotificationService').getListNotification().forEach(function(notif) {
                notif.isRead = true;
            });
            return NotificationWS.readAllNotification(idUser);
        };

        return svc;
    })
})();