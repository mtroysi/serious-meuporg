/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService, $stateParams, constant, AuthenticationService) {
            var ctrl = this;
            ctrl.priority = constant.priority;

            ctrl.init = function () {
                ctrl.task = {};
                ctrl.comment = {};
                ctrl.showTask($stateParams.id);
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
                    ctrl.task.taskComments.splice(idx,1);
                });
            };

            ctrl.init();
        })
})();