/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TaskShowService, $stateParams) {
            var ctrl = this;

            ctrl.init = function () {
                ctrl.task = {};
                ctrl.showTask($stateParams.id);
            };

            ctrl.showTask = function (id) {
                TaskShowService.showTask(id).then(function (data) {
                    ctrl.task = data;
                });
            };


            ctrl.init();
        })
})();