(function() {
    'use strict';

    var helloApp = angular.module('hello');

    // Pour chaque chargement d'une nouvelle URL on va regarder si l'utilisateur a le droit de si connecter
    helloApp.run(function($rootScope, $state, CommonDialogService, AuthenticationService) {

        $rootScope.$on('$stateChangeStart', function(event, toState, toParams) {
            console.log("---->", AuthenticationService.getAuthorization());
            //On regarde si il y a le parametre requireLogin
            if (toState && toState.data && typeof toState.data.requireLogin !== 'undefined') {
                if (toState.data.requireLogin === true && !AuthenticationService.getAuthorization()) {
                    event.preventDefault();
                    CommonDialogService.error("Désolé mais il faut être connecté pour arriver sur cette fonctionnalitée.", "Accès interdit");
                    $state.go('appSimple.home');
                }
            }
        });
    });

    // Service qui va stocker le token de connexion
    helloApp.service('AuthenticationService', function($cookies) {
        var svc = {};

        svc.getAuthorization = function() {
            return $cookies.get('Authorization');
        };
        svc.setAuthorization = function(val) {
            $cookies.put('Authorization', val);
        };
        svc.getUserId = function() {
            return $cookies.get('user_id');
        };
        svc.setUserId = function(val) {
            $cookies.put('user_id', val);
        };

        return svc;
    });

    // AJout dans tous les Header l'autorisation (token de connexion)
    helloApp.factory('AuthorizationInjector', function(AuthenticationService) {
        var sessionInjector = {
            request: function(config) {
                if (AuthenticationService.getAuthorization()) {
                    config.headers['Authorization'] = AuthenticationService.getAuthorization();
                }
                return config;
            }
        };
        return sessionInjector;
    });

    helloApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('AuthorizationInjector');
    }]);
})();