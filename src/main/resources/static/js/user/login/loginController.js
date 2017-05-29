(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('LoginController', function( $state,$rootScope, LoginService) {
        var loginCtrl = this;

        loginCtrl.init = function() { /** constructeur (pseudo objet) */
            loginCtrl.credentials = [];
        };

        /**
         * Login
         */
        loginCtrl.loginAction = function() {
            var user = {
                email: loginCtrl.credentials.email,
                password: loginCtrl.credentials.password
            };
            LoginService.authentification(user).then(function(data) { 
                /** appel aux methodes du services */
                $state.go('app.dashboard');
            });
        };

        loginCtrl.init();
    })
})();