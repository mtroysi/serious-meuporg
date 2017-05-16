(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('SigninController', function ($state, AuthenticationService) {
        var signinCtrl = this;

        signinCtrl.init = function () { /** constructeur (pseudo objet) */
            AuthenticationService.setUserId(null);
            AuthenticationService.setAuthorization(null);
            $state.go('appSimple.home');
        };
        signinCtrl.init();
    })
})();