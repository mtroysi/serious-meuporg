(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('SignupService', function (SignupWS) {
        var svc = {};

        /**
         * Inscription
         * @param user utilisateur à créer
         */
        svc.signup = function (user) {
            return SignupWS.createUser(user).then(function (response) {
                return response.data;
            })
        };

        return svc;
    })
})();
