(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationService', function() {
        var svc = {};

        svc.listNotification = [];

        svc.getListNotification = function() {
            return svc.listNotification;
        };

        svc.addListNotification = function(element, bdd) {
            if (!svc.listNotification) {
                svc.listNotification = [];
            }
            svc.listNotification.push(element);

            // The notification is saved in the database
            if (bdd === true) {

            }
        };

        svc.updateElementListNotification = function(newElement) {
            var index = svc.listNotification.findIndex(function(element) { return element.id == newElement.id });
            if (index != -1) {
                svc.listNotification[index] = newElement;
            }
        };

        svc.removeListNotification = function(notification_id) {
            var index = _.findIndex(svc.listNotification, { id: notification_id });
            svc.listNotification.splice(index, 1);
        };

        svc.initListNotification = function(elements) {
            svc.listNotification = elements;
        };

        return svc;
    });
})();