/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskCreateController', function (TaskCreateService) {
            var ctrl = this;

            ctrl.init = function () {
                ctrl.task = {};
            };

            ctrl.createTask = function () {
                ctrl.task.dateCreation = Date.now();
                TaskCreateService.createTask(ctrl.task).then(function (data) {
                    ctrl.task = data;
                })
            };

            ctrl.init();
        })
})();