/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskUpdateWS', function ($http, constant) {
            var svc = this;

            svc.createTask = function (task) {
                return $http.post(constant.BASE_URI + '/taskUser/', task);
            };

            svc.updateTask = function (id, task) {
                return $http.put(constant.BASE_URI + '/taskUser/' + id, task);
            };

            svc.deleteTask = function (id) {
                return $http.delete(constant.BASE_URI + '/taskUser/' + id);
            };

            svc.toggleTag = function (idTask, idTag) {
                return $http.put(constant.BASE_URI + '/task/' + idTask + '/tag/' + idTag);
            };

            return svc;
        })
})();