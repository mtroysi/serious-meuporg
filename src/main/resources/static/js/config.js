(function () {
    'use strict';

    angular.module('hello').run(function ($rootScope, $state, loginModal) {

        $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
            //On regarde si il y a le parametre requireLogin
            if (toState && toState.data && typeof toState.data.requireLogin !== 'undefined') {
                if (toState.data.requireLogin !== true && typeof $rootScope.currentUser === 'undefined') {
                    event.preventDefault();
                    $state.go('app.home');
                }
            }
          
        });

    });
});