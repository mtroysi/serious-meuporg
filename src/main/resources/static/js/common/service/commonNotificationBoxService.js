(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonNotificationBoxService', function(toastr) {
        var svc = {};

        svc.info = function(title, message) {
            toastr.info(title, message);
        };

        svc.notice = function(title, message) {
            toastr.warning(title, message);
        };

        svc.success = function(title, message) {
            toastr.success(title, message);
        };

        svc.error = function(title, message) {
            toastr.error(title, message);
        };

        svc.messageAdore = function(title) {
            svc.info(title, '');
        };

        return svc;
    })
})();