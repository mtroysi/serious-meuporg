/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService, TaskUpdateService, $stateParams, constant, AuthenticationService, TagListService, $scope) {
            var ctrl = this;

            $scope.$on("showTask", function (event, args) {
                ctrl.newTask = args.task.task;
                ctrl.task = angular.copy(ctrl.newTask);
                ctrl.columns = args.colonneKanban;
                ctrl.listTags();
            });

            ctrl.priority = constant.priority;

            ctrl.init = function () {
                ctrl.comment = {};
                ctrl.tags = [];
            };

            ctrl.showTask = function (id) {
                TaskShowService.showTask(id).then(function (data) {
                    ctrl.task = data;
                });
            };

            ctrl.addComment = function () {
                ctrl.comment.dateCreation = Date.now();
                ctrl.creator = AuthenticationService.getUserId();
                return TaskShowService.addComment($stateParams.id, ctrl.comment, ctrl.creator).then(function (data) {
                    ctrl.task.taskComments.push(data);
                    ctrl.comment = {};
                });
            };

            ctrl.deleteComment = function (idx) {
                return TaskShowService.deleteComment(ctrl.task.taskComments[idx].id).then(function () {
                    ctrl.task.taskComments.splice(idx, 1);
                });
            };

            ctrl.updateComment = function (idx) {
                return TaskShowService.updateComment(ctrl.task.taskComments[idx].id, ctrl.task.taskComments[idx]).then(function (data) {
                    ctrl.task.taskComments[idx] = data;
                });
            };

            ctrl.updateTask = function () {
                TaskUpdateService.updateTask(ctrl.task.id, ctrl.task).then(function (data) {
                    console.log(ctrl.task);
                    ctrl.newTask = data;
                    console.log(ctrl.newTask);
                    $("#editTask").modal('toggle');
                });
            };

            ctrl.listTags = function () {
                TagListService.listTags().then(function (data) {
                    ctrl.tags = data;
                    ctrl.setSelectedTask();
                });
            };

            ctrl.setSelectedTask = function () {
                ctrl.task.tags.forEach(function (element) {
                    ctrl.tags.forEach(function (bis) {
                        if (element.color === bis.color) {
                            bis.selected = true;
                        }
                    });
                });
            };

            ctrl.toggleTag = function (tag) {
                TaskUpdateService.toggleTag(ctrl.task.id, tag.id).then(function (data) {
                    ctrl.task = data;
                    tag.selected = !tag.selected;
                });
            };

            ctrl.init();
        })
})();