/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationWS', function($http, constant) {
        var svc = this;

        /**
         * Retourne les notifications d'un utilisateur
         * @param idUser id de l'utilisateur
         * @returns {HttpPromise}
         */
        svc.getNotificationByUser = function(idUser) {
            return $http.get(constant.BASE_URI + '/notification/user/' + idUser);
        };

        /**
         * Crée une notification
         * @param notification données de la notif à créer
         * @returns {HttpPromise}
         */
        svc.createNotification = function(notification) {
            return $http.post(constant.BASE_URI + '/notification/', notification);
        };

        /**
         * Marque toutes les notifications de l'utilisateur comme lues
         * @param idUser id de l'utilisateur
         * @returns {HttpPromise}
         */
        svc.readAllNotification = function(idUser) {
            return $http.get(constant.BASE_URI + '/notification/user/' + idUser + '/read');
        };

        return svc;

    })
})();