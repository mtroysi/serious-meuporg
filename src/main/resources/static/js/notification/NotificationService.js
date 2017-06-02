/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationService', function(NotificationWS, $injector) {
        var svc = {};

        /**
         * Retourne les notifications d'un utilisateur
         * @param id l'id de l'utilisateur
         */
        svc.getNotificationByUser = function(id) {
            return NotificationWS.getNotificationByUser(id).then(function(response) {
                return response.data;
            });
        };

        /**
         * Crée une notification
         * @param notification données de la notif à créer
         */
        svc.createNotification = function(notification) {
            return NotificationWS.createNotification(notification).then(function(response) {
                return response.data;
            });
        };

        /**
         * Marque toutes les notifications de l'utilisateur comme lues
         * @param idUser id de l'utilisateur
         */
        svc.readAllNotification = function(idUser) {
            var list = $injector.get('CommonNotificationService').getListNotification() || [];
            list.forEach(function(notif) {
                notif.isRead = true;
            });
            return NotificationWS.readAllNotification(idUser);
        };

        return svc;
    })
})();