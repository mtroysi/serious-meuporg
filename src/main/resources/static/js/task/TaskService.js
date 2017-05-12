/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskService', function (TaskWS) {
            var svc = {};

            svc.createTask = function (name) {
                return TaskWS.createTask(name).then(function (response) {
                    return response.data;
                })
            };

            svc.updateTask = function (id, jsonToSend) {
                return TaskWS.updateTask(id, jsonToSend).then(function (response) {
                    return response.data;
                })
            };

            return svc;
        })
})();