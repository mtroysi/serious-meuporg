/**
 * Created by Sara ZALARHE on 29/05/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('ProfilController', function($location, $http, $state, $stateParams, CommonNotificationBoxService, UserService, AuthenticationService, TaskService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.user = {};
            ctrl.stats = 0;
            ctrl.rankin = 0;
            ctrl.listUsers = [];
            UserService.getUser($stateParams.idUser).then(function(response) {
                ctrl.user = response;
                if (!ctrl.user.avatar) {
                    ctrl.user.avatar = "images/avatar/user.png";
                }
            });
            ctrl.getRankin();
        };

        ctrl.getRankin = function() {
            UserService.getRankin(AuthenticationService.getUserId()).then(function(response) {
                ctrl.rankin = response;
            });
        };

        ctrl.init();
    })
})();