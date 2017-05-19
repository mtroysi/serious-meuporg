/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationWS', function($http, constant) {
        var svc = this;

        svc.getNotificationByUser = function(id) {
            return $http.get(constant.BASE_URI + '/notification/user/' + id);
        };

        return svc;

    })
})();