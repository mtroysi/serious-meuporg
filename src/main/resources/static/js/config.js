(function() {
    'use strict';

    var helloApp = angular.module('hello');

    // Pour chaque chargement d'une nouvelle URL on va regarder si l'utilisateur a le droit de si connecter
    helloApp.run(function($rootScope, $state, CommonDialogService, AuthenticationService, moment, CommonItemService) {

        $rootScope.$on('$viewContentLoaded', function() {
            CommonItemService.run();
        });

        $rootScope.$on('$stateChangeStart', function(event, toState, toParams) {
            //On regarde si il y a le parametre requireLogin
            if (toState && toState.data && typeof toState.data.requireLogin !== 'undefined') {
                // connexion obligatoire
                if (toState.data.requireLogin === true && !AuthenticationService.getAuthorization()) {
                    event.preventDefault();
                    CommonDialogService.error("Connectez-vous pour avoir accès à cette fonctionnalité.", "Accès interdit");
                    $state.go('appSimple.home');
                }
                // pas le droit d'aller sur home/login/inscripion
                if (AuthenticationService.getAuthorization() && (toState.data.requireLogin === false)) {
                    event.preventDefault();
                    $state.go('app.dashboard');
                }
            }
        });
    });

    // Service qui va stocker le token de connexion
    helloApp.service('AuthenticationService', function($cookies) {
        var svc = {};

        svc.getAuthorization = function() {
            if ($cookies.get('Authorization') === "null") {
                return null;
            } else {
                return $cookies.get('Authorization')
            }

        };
        svc.setAuthorization = function(val) {
            $cookies.put('Authorization', val);
        };
        svc.getUserId = function() {
            if ($cookies.get('user_id') === "null") {
                return null;
            } else {
                return $cookies.get('user_id');
            }


        };
        svc.setUserId = function(val) {
            $cookies.put('user_id', val);
        };
        svc.empty = function() {
            $cookies.put('Authorization', null);
            $cookies.put('user_id', null);
        };

        return svc;
    });

    // AJout dans tous les Header l'autorisation (token de connexion)
    helloApp.factory('AuthorizationInjector', function(AuthenticationService, CommonNotificationBoxService, $q) {
        var sessionInjector = {
            request: function(config) {
                if (AuthenticationService.getAuthorization()) {
                    config.headers['Authorization'] = AuthenticationService.getAuthorization();
                }
                return config;
            },
            responseError: function(response) {
                console.log(response);
                CommonNotificationBoxService.error("Erreur", response.data.message);
                return $q.reject(response.message);
            }
        };
        return sessionInjector;


    });

    helloApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('AuthorizationInjector');
    }]);

    helloApp.config(["toastrConfig",function(toastrConfig) {

        var options = {
            "closeButton": true,
            "progressBar": true
        };

        angular.extend(toastrConfig, options);
    }]);

})();