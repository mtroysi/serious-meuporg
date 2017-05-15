(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationService', function() {
        var svc = {};

        svc.info = function(title, message) {
            svc.notice(title, message, 'info');
        };

        svc.notice = function(title, message) {
            svc.notice(title, message, 'notice');
        };

        svc.success = function(title, message) {
            svc.notice(title, message, 'success');
        };

        svc.error = function(title, message) {
            svc.notice(title, message, 'error');
        };

        svc.notice = function(title, message, type) {
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