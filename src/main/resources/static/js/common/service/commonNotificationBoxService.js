(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationBoxService', function() {
        var svc = {};

        /**
         * Notification d'information
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.info = function(title, message) {
            svc.message(title, message, 'info');
        };

        svc.notice = function(title, message) {
            svc.message(title, message, 'notice');
        };

        /**
         * Notification de succès
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.success = function(title, message) {
            svc.message(title, message, 'success');
        };

        /**
         * Notification d'erreur
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.error = function(title, message) {
            svc.message(title, message, 'error');
        };

        /**
         * Notification spécifique au sort de bonne humeur
         * @param title titre de la notification
         */
        svc.messageAdore = function(title) {
            svc.message(title, '', 'info', 7000);
        };

        /**
         * Options du contenu des notifications
         * @param title titre
         * @param message contenu
         * @param type error / info / success
         * @param delay délai d'affichage
         */
        svc.message = function(title, message, type, delay) {
            new PNotify({
                title: title,
                text: message,
                type: type,
                icon: false,
                delay: delay || 3000,
                styling: 'fontawesome'
            });
        };

        return svc;
    })
})();