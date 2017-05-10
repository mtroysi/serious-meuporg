/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('HomeWS', function ($http, constant) {
        var svc = this;

        svc.getResource = function () {
            return $http.get(constant.BASE_URI + '/resource/');
        };

        return svc;
    })
})();