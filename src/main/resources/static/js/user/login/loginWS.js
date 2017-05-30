(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('LoginWS', function($http, constant) {
        var svc = this;

        /**
         * Login
         * @param user
         * @returns {HttpPromise}
         */
        svc.login = function(user) {
            return $http.post(constant.BASE_URI + '/user/login', user);
        };

        return svc;
    })
})();