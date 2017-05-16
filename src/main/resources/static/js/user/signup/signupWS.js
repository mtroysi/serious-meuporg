(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('SignupWS', function ($http, constant) {
        var svc = this;

        svc.createUser = function (user) {
           return $http.post(constant.BASE_URI + '/user', user);
        };

        return svc;
    })
})();