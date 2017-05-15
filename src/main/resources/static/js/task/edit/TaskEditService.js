/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskEditService', function (TaskEditWS) {
            var svc = {};

            svc.editTask = function (id, task) {
                return TaskEditWS.editTask(id, task).then(function (response) {
                    return response.data;
                });
            };


            return svc;
        })
})();