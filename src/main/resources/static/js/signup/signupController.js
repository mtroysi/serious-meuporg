(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('signupController', function($rootScope, SignupService) {
        var signupCtrl = this;

        signupCtrl.init = function() { /** constructeur (pseudo objet) */
            signupCtrl.credentials = [];
        };

        signupCtrl.signupAction = function() {
            var user={
                    firstName: signupCtrl.credentials.firstName,
                    lastName: signupCtrl.credentials.lastName,
                    email: signupCtrl.credentials.email,
                    password: signupCtrl.credentials.password,
            }
            SignupService.signup(user).then(function(data) { /** appel aux methodes du services */
               
            });
        };

        signupCtrl.init();
    })
})();