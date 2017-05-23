/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService,TaskUpdateService, $stateParams, constant, AuthenticationService, $scope) {
            var ctrl = this;

            $scope.$on("showTask", function (event, args) {
               ctrl.task = args.task;
               console.log(ctrl.task);
            });

            ctrl.priority = constant.priority;

            ctrl.init = function () {
                ctrl.comment = {};
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
                    console.log(data);
                    ctrl.task.taskComments[idx] = data;
                });
            };

            ctrl.updateTask = function () {
                console.log("tesst");
                TaskUpdateService.updateTask(ctrl.task.id, ctrl.task).then(function (data) {
                    ctrl.task = data;
                    $("#editTask").modal('toggle');
                });
            };

            ctrl.init();
        })
})();