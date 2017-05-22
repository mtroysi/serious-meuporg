/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('DashboardController', function($location, $http, $state, CommonNotificationBoxService, UserService, AuthenticationService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.user = {};
            ctrl.editorEnabled = false;
            var id = AuthenticationService.getUserId();
            UserService.getUser(id).then(function(response) {
                ctrl.user = response;
                if (!ctrl.user.avatar) {
                    ctrl.user.avatar = "images/avatar/user.png";
                }
            });
        };

        ctrl.enableEditor = function() {
            ctrl.editorEnabled = true;
        };

        ctrl.editUserAction = function() {
            var user = {
                firstName: ctrl.user.firstName,
                lastName: ctrl.user.lastName
            };

            UserService.editUser(ctrl.user).then(function(data) { /** appel aux methodes du services */
                if (data) {
                    CommonNotificationBoxService.success("Information", "Vos informations ont bien été modifiées");
                    ctrl.editorEnabled = false;
                }
            });
        };

        ctrl.init();
    })
})();