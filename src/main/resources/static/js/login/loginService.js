(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('LoginService', function (LoginWS) {
        var svc = {};

        svc.authentification = function (email,password) {
           return LoginWS.authentification().then(function (response) {
                return response.data;
            })
        };

        return svc;
    })
})();
