/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('DashboardController', function($location, $http, UserService, AuthenticationService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.user={};
            var id = AuthenticationService.getUserId();
            UserService.getUser(id).then(function(response){
                ctrl.user = response;
            });
        };

        ctrl.init();
    })
})();