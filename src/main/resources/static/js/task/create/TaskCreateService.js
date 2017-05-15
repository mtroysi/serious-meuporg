/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskCreateService', function (TaskCreateWS) {
            var svc = {};

            svc.createTask = function (newTask) {
                return TaskCreateWS.createTask(newTask).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        });
})();