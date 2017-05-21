(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('SignupController', function($state, SignupService, CommonNotificationBoxService) {
        var signupCtrl = this;

        signupCtrl.init = function() { /** constructeur (pseudo objet) */
            signupCtrl.credentials = [];
        };

        signupCtrl.signupAction = function() {
            var user = {
                firstName: signupCtrl.credentials.firstName,
                lastName: signupCtrl.credentials.lastName,
                email: signupCtrl.credentials.email,
                password: signupCtrl.credentials.password,
            };
            SignupService.signup(user).then(function(data) { /** appel aux methodes du services */
                if (data != null) {
                    CommonNotificationBoxService.success("Information", "votre compte à bien été créé");
                    $state.go('appSimple.login');
                }
            });
        };

        signupCtrl.init();
    })
})();