(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('LoginService', function(LoginWS, AuthenticationService) {
        var svc = {};

        svc.authentification = function(user) {

            return LoginWS.login(user).then(function(response) {
                var header = response.headers();
                if (header) {
                    AuthenticationService.setUserId(header.user_id);
                    AuthenticationService.setAuthorization(header.authorization ? header.authorization : null);
                }

                return response.data;
            })
        };

        return svc;
    })
})();