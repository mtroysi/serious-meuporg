/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('DashboardController', function(constant, $location, TaskShowService, $http, $state, $stateParams, CommonNotificationBoxService, UserService, AuthenticationService) {
        var ctrl = this;

        /**
         * Initialisation.
         */
        ctrl.init = function() {
            ctrl.user = {};
            ctrl.stats = 0;
            ctrl.rankin = 0;
            ctrl.listTask = [];
            ctrl.listTaskBid = [];
            ctrl.listUsers = [];
            ctrl.listTaskDefault = [];
            ctrl.editorEnabled = false;
            ctrl.task = {};
            var id = AuthenticationService.getUserId();
            UserService.getUser(id).then(function(response) {
                ctrl.user = response;
                if (!ctrl.user.avatar) {
                    ctrl.user.avatar = constant.URL_IMAGE_DEFAULT;
                }
            });
            ctrl.getStats();
            ctrl.getRankin();
            ctrl.getTaskUser(AuthenticationService.getUserId());
            ctrl.getTopUsers();
            ctrl.getTaskUserBird(AuthenticationService.getUserId());
        };

        /**
         * Affiche ou non le formulaire d'édition des données de l'utilisateur
         */
        ctrl.enableEditor = function() {
            ctrl.editorEnabled = true;
        };

        /**
         * Edition des informations utilisateur
         */
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

        /**
         * Retourne les statistiques de l'utilisateurœ
         */
        ctrl.getStats = function() {
            UserService.getStats(AuthenticationService.getUserId()).then(function(response) {
                ctrl.stats = response;
            });
        };

        /**
         * Retourne le rang de l'utilisateur dans les différentes catégories
         */
        ctrl.getRankin = function() {
            UserService.getRankin(AuthenticationService.getUserId()).then(function(response) {
                ctrl.rankin = response;
            });
        };

        /**
         * Retourne les tâches de l'utilisateur
         * @param user_id id de l'utilisateur
         */
        ctrl.getTaskUser = function(user_id) {
            TaskShowService.listTaskByUser(user_id).then(function(fetchData) {
                /* ctrl.listTaskDefault = angular.copy(ctrl.addColorTask(fetchData));*/
                ctrl.listTask = fetchData;
            });

        };

        /**
         * Retourne les tâches en enchére de l'utilisateur
         * @param user_id id de l'utilisateur
         */
        ctrl.getTaskUserBird = function(user_id) {
            TaskShowService.listTaskByUserAndBoard(user_id).then(function(fetchData) {
                ctrl.listTaskBid = fetchData;
            });

        };


        /**
         * Retourne la liste des meilleurs joueurs dans chaque catégorie
         */
        ctrl.getTopUsers = function() {
            UserService.getTopUsers().then(function(fetchData) {
                ctrl.listUsers = fetchData;
            });

        };

        /**
         * Affiche la tâche et redirige vers son tableau
         * @param task tâche à afficher
         */
        ctrl.taskAction = function(task) {
            $state.go('app.board-preview', { idBoard: task.task.boardId, idtask: task.task.id });

        };

        ctrl.userAction = function(user) {
            $state.go('app.profil', { idUser: user.id });
        }
        ctrl.taskActionBid = function(task) {
            console.log(task);
            $state.go('app.bidPreview', { idBoard: task.board.id });
        }

        ctrl.init();
    })
})();