/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('NotificationWS', function($http, constant) {
        var svc = this;

        svc.getNotificationByUser = function(idUser) {
            return $http.get(constant.BASE_URI + '/notification/user/' + idUser);
        };

        svc.createNotification = function(notification) {
            return $http.post(constant.BASE_URI + '/notification/', notification);
        };

        svc.readAllNotification = function(idUser) {
            return $http.get(constant.BASE_URI + '/notification/user/' + idUser + '/read');
        };

        return svc;

    })
})();