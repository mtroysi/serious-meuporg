/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskController', function (TaskService) {
            var ctrl = this;

            ctrl.init = function () {
                ctrl.task = {};
            };

            ctrl.createTask = function () {
                ctrl.task.dateCreation = Date.now();
                TaskService.createTask(ctrl.task).then(function (data) {
                    ctrl.task = data;
                })
            };

            ctrl.init();
        })
})();