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

        svc.messageAdore = function(title) {
            svc.message(title, '', 'info', 7000);
        };

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