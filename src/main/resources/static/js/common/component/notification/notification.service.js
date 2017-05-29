(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationService', function(NotificationService, AuthenticationService) {
        var svc = {};

        svc.listNotification = [];

        /**
         * Retourne la liste des notifications
         * @returns {Array|*}
         */
        svc.getListNotification = function() {
            return svc.listNotification;
        };

        /**
         * Ajoute une notification à la liste
         * @param element notification
         * @param bdd boolean flag (doit être sauvegardé en bdd ou non)
         */
        svc.addListNotification = function(element, bdd) {
            if (!svc.listNotification) {
                svc.listNotification = [];
            }

            // The notification is saved in the database
            if (bdd === true) {
                element.idUser = AuthenticationService.getUserId();
                NotificationService.createNotification().then(function(response) {
                    svc.listNotification.push(response);
                });
            } else {
                svc.listNotification.push(response);
            }
        };

        /**
         * Modifie une notification
         * @param newElement notification modifiée
         */
        svc.updateElementListNotification = function(newElement) {
            var index = svc.listNotification.findIndex(function(element) { return element.id == newElement.id });
            if (index != -1) {
                svc.listNotification[index] = newElement;
            }
        };

        /**
         * Retire une notification de la liste
         * @param notification_id id de la notif à enlever
         */
        svc.removeListNotification = function(notification_id) {
            var index = _.findIndex(svc.listNotification, { id: notification_id });
            svc.listNotification.splice(index, 1);
        };

        /**
         * Initialise la liste de notifications avec une liste d'éléments
         * @param elements notifications
         */
        svc.initListNotification = function(elements) {
            svc.listNotification = elements;
        };

        return svc;
    });
})();