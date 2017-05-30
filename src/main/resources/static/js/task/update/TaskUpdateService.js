/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskUpdateService', function (TaskUpdateWS) {
            var svc = {};

            svc.createTask = function (task) {
                return TaskUpdateWS.createTask(task).then(function (response) {
                    return response.data;
                });
            };

            svc.updateTask = function (id, task) {
                return TaskUpdateWS.updateTask(id, task).then(function (response) {
                    return response.data;
                });
            };

            svc.deleteTask = function (id) {
                return TaskUpdateWS.deleteTask(id).then(function (response) {
                    return response.data;
                });
            };

            svc.toggleTag = function (idTask, idTag) {
                return TaskUpdateWS.toggleTag(idTask, idTag).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        })
})();