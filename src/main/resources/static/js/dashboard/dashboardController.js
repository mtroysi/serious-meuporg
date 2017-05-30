/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('DashboardController', function($location, $http, $state, $stateParams, CommonNotificationBoxService, UserService, AuthenticationService, TaskShowService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.user = {};
            ctrl.stats = 0;
            ctrl.rankin = 0;
            ctrl.listTask = [];
            ctrl.listUsers = [];
            ctrl.listTaskDefault = [];
            ctrl.editorEnabled = false;
            ctrl.task = {};
            var id = AuthenticationService.getUserId();
            UserService.getUser(id).then(function(response) {
                ctrl.user = response;
                if (!ctrl.user.avatar) {
                    ctrl.user.avatar = "images/avatar/user.png";
                }
            });
            ctrl.getStats();
            ctrl.getRankin();
            ctrl.getTaskUser(AuthenticationService.getUserId());
            ctrl.getTopUsers();
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

        ctrl.getStats = function() {
            UserService.getStats(AuthenticationService.getUserId()).then(function(response) {
                ctrl.stats = response;
            });
        };

        ctrl.getRankin = function() {
            UserService.getRankin(AuthenticationService.getUserId()).then(function(response) {
                ctrl.rankin = response;
            });
        };

        ctrl.getTaskUser = function(user_id) {
            TaskShowService.listTaskByUser(user_id).then(function(fetchData) {
                /** ctrl.listTaskDefault = angular.copy(ctrl.addColorTask(fetchData));*/
                ctrl.listTask = fetchData;
            });

        };

         ctrl.getTopUsers = function() {
            UserService.getTopUsers().then(function(fetchData) {
                ctrl.listUsers = fetchData;
            });

        };

        ctrl.taskAction = function(task) {
            $state.go('app.board-preview', { idBoard: task.task.boardId, idtask: task.task.id });

        };

        ctrl.userAction = function(user){
              $state.go('app.profil', { idUser: user.id });
        }

        ctrl.init();
    })
})();