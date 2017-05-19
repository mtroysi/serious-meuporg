/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationService', function(NotificationWS) {
        var svc = {};

        return svc;
    })
})();