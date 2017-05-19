(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationBoxService', function() {
        var svc = {};

        svc.info = function(title, message) {
            svc.message(title, message, 'info');
        };

        svc.notice = function(title, message) {
            svc.message(title, message, 'notice');
        };

        svc.success = function(title, message) {
            svc.message(title, message, 'success');
        };

        svc.error = function(title, message) {
            svc.message(title, message, 'error');
        };

        svc.message = function(title, message, type) {
            new PNotify({
                title: title,
                text: message,
                type: type,
                icon: false,
                delay: 3000,
                styling: 'fontawesome'
            });
        };

        return svc;
    })
})();