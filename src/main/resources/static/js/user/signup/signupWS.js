(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('SignupWS', function ($http, constant) {
        var svc = this;

        /**
         * Crée un utilisateur
         * @param user utilisateur à créer
         * @returns {HttpPromise}
         */
        svc.createUser = function (user) {
           return $http.post(constant.BASE_URI + '/user', user);
        };

        return svc;
    })
})();