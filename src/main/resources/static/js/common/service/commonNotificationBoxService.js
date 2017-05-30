(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationBoxService', function(toastr) {
        var svc = {};

        /**
         * Notification d'information
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.info = function(title, message) {
            toastr.info(title, message);
        };

        svc.notice = function(title, message) {
            toastr.warning(title, message);
        };

        /**
         * Notification de succès
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.success = function(title, message) {
            toastr.success(title, message);
        };

        /**
         * Notification d'erreur
         * @param title titre de la notification
         * @param message contenu de la notification
         */
        svc.error = function(title, message) {
            toastr.error(title, message);
        };

        /**
         * Notification spécifique au sort de bonne humeur
         * @param title titre de la notification
         */
        svc.messageAdore = function(title) {
            svc.info(title, '');
        };

        return svc;
    })
})();