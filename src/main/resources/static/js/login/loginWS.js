(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('LoginWS', function ($http, constant) {
        var svc = this;

        svc.getResource = function () {
            return $http.get(constant.BASE_URI + '/resource/');
        };

        return svc;
    })
})();