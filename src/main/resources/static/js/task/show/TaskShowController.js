/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService, $stateParams, constant) {
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

            }

            ctrl.init();
        })
})();