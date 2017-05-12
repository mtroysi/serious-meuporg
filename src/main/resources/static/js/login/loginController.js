
(function () {
    'use strict';

    var helloApp = angular.module('hello', ['ui.router']);

    /** @ngInject */
    helloApp.controller('LoginController', function ($rootScope, LoginService) {
        var loginCtrl = this;

        loginCtrl.init = function () { /** constructeur (pseudo objet) */
            loginCtrl.credentials = [];
        };

        loginCtrl.loginAction = function() {
            LoginService.authentification(loginCtrl.email, loginCtrl.password).then(function (data) {/** appel aux methodes du services */
                if (data.email) {
                    $rootScope.currentUser = true;
                } else {
                    $rootScope.currentUser = false;
                }
               /* if ($rootScope.currentUser) {
          $location.path("/");
          loginCtrl.error = false;
        } else {
          $location.path("/login");
          loginCtrl.error = true;
            }*/
            });
        };

        loginCtrl.init();
    })
})();