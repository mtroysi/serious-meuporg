/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService, TaskUpdateService, $stateParams, constant, AuthenticationService, TagListService, $scope, $rootScope) {
            var ctrl = this;

            ctrl.popup1 = {
                opened: false
            };

            ctrl.popup2 = {
                opened: false
            };

            ctrl.open = function (i) {
                switch (i) {
                    case 1: {
                        ctrl.popup1.opened = true;
                        break;
                    }
                    case 2: {
                        ctrl.popup2.opened = true;
                        break;
                    }
                }
            };

            $scope.$on("showTask", function (event, args) {
                ctrl.newTask = args.task;
                ctrl.task = angular.copy(ctrl.newTask);
                ctrl.columns = args.colonneKanban;
                console.log(ctrl.task);
                ctrl.listTags();
                ctrl.titleEditable = false;
                ctrl.creation = false;
            });

            $scope.$on("createTask", function (event, args) {
                ctrl.newTask = args.task;
                ctrl.task = angular.copy(ctrl.newTask);
                ctrl.columns = args.colonneKanban;
                ctrl.task.task.isPeriodicity = false;
                ctrl.listTags();
                ctrl.titleEditable = true;
                ctrl.creation = true;
            });

            ctrl.priority = constant.priority;
            ctrl.periodicityType = constant.periodicity;

            ctrl.init = function () {
                ctrl.comment = {};
                ctrl.comment.content = "";
                ctrl.tags = [];
                ctrl.titleEditable = false;
            };

            ctrl.toggleTitle = function () {
                if (ctrl.task.task.title !== undefined)
                    ctrl.titleEditable = !ctrl.titleEditable;
            };

            /**
             * Récupère la tâche à afficher
             * @param id
             */
            ctrl.showTask = function (id) {
                TaskShowService.showTask(id).then(function (data) {
                    ctrl.task = data;
                });
            };

            /**
             * Ajoute un commentaire
             */
            ctrl.addComment = function () {
                ctrl.comment.dateCreation = Date.now();
                ctrl.creator = AuthenticationService.getUserId();
                return TaskShowService.addComment(ctrl.task.task.id, ctrl.comment, ctrl.creator).then(function (data) {
                    ctrl.task.task.taskComments.push(data);
                    ctrl.comment = {};
                });
            };

            /**
             * Supprime un commentaire
             * @param idx
             */
            ctrl.deleteComment = function (idx) {
                return TaskShowService.deleteComment(ctrl.task.task.taskComments[idx].id).then(function () {
                    ctrl.task.task.taskComments.splice(idx, 1);
                });
            };

            /**
             * Modifie un commentaire
             * @param idx
             */
            ctrl.updateComment = function (idx) {
                return TaskShowService.updateComment(ctrl.task.task.taskComments[idx].id, ctrl.task.task.taskComments[idx]).then(function (data) {
                    ctrl.task.task.taskComments[idx] = data;
                });
            };

            ctrl.performActionTask = function () {
                if (ctrl.creation) {
                    ctrl.createTask();
                } else {
                    ctrl.updateTask();
                }
            };

            ctrl.createTask = function () {
                if (ctrl.task.task.isPeriodicity) {
                    ctrl.task.task.periodicity.dateBegin = new Date(ctrl.task.task.periodicity.dateBegin).getTime();
                }
                ctrl.task.task.dateEnd = new Date(ctrl.task.task.dateEnd).getTime();
                TaskUpdateService.createTask(ctrl.task).then(function (data) {
                    var args = {};
                    args.task = data;
                    $rootScope.$broadcast('createdTask', args);
                    $("#editTask").modal('toggle');
                });
            };

            /**
             * Modifie une tâche
             */
            ctrl.updateTask = function () {
                if (ctrl.task.task.isPeriodicity) {
                    ctrl.task.task.periodicity.dateBegin = new Date(ctrl.task.task.periodicity.dateBegin).getTime();
                }
                ctrl.task.task.dateEnd = new Date(ctrl.task.task.dateEnd).getTime();
                TaskUpdateService.updateTask(ctrl.task.id, ctrl.task).then(function (data) {
                    angular.extend(ctrl.newTask, ctrl.newTask, data);
                    $("#editTask").modal('toggle');
                });
            };

            ctrl.cancelTask = function () {
                $("#editTask").modal('toggle');
            };

            ctrl.confirmDeleteTask = function () {
                TaskUpdateService.deleteTask(ctrl.task.id).then(function () {
                    var args = {};
                    args.task = ctrl.task;
                    $rootScope.$broadcast('deleteTask', args);
                    $("#editTask").modal('toggle');
                });
            };

            /**
             * Récupère la liste des tags
             */
            ctrl.listTags = function () {
                TagListService.listTags().then(function (data) {
                    ctrl.tags = data;
                    ctrl.setSelectedTask();
                });
            };

            /**
             * Modifie l'affichage des tags : marque ceux sélectionnés
             */
            ctrl.setSelectedTask = function () {
                if (ctrl.task.task !== undefined && ctrl.task.task.tags !== undefined) {
                    ctrl.task.task.tags.forEach(function (element) {
                        ctrl.tags.forEach(function (bis) {
                            if (element.color === bis.color) {
                                bis.selected = true;
                            }
                        });
                    });
                }
            };

            /**
             * Ajoute ou enlève un tag d'une tâche
             * @param tag
             */
            ctrl.toggleTag = function (tag) {
                if (ctrl.task.task.id !== undefined) {
                    TaskUpdateService.toggleTag(ctrl.task.id, tag.id).then(function (data) {
                        ctrl.task.task = data;
                        tag.selected = !tag.selected;
                    });
                } else {
                    if (ctrl.task.task.tags === undefined)
                        ctrl.task.task.tags = [];
                    ctrl.task.task.tags.push(tag);
                }
            };

            ctrl.init();
        })
})();