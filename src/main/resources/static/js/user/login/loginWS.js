(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('LoginWS', function($http, constant) {
        var svc = this;

        svc.login = function(user) {
            return $http.post(constant.BASE_URI + '/user/login', user);
        };

        return svc;
    })
})();