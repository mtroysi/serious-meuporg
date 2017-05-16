/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskUpdateController', function (TaskShowService, TaskUpdateService, $stateParams, constant) {
            var ctrl = this;

            ctrl.priority = constant.priority;
            ctrl.duree = constant.duree;

            ctrl.init = function () {
                ctrl.task = {};
                TaskShowService.showTask($stateParams.id).then(function (data) {
                    ctrl.task = data;
                });
            };

            ctrl.editTask = function () {
                TaskUpdateService.updateTask($stateParams.id, ctrl.task).then(function (data) {
                    ctrl.task = data;
                });
            };


            ctrl.init();
        })
})();