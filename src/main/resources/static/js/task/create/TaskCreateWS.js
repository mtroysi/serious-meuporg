/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskCreateWS', function ($http, constant) {
            var svc = this;

            svc.createTask = function (newTask) {
                console.log(newTask);
                return $http.post(constant.BASE_URI + '/task/', newTask);
            };

            svc.updateTask = function (id, jsonToSend) {
                return $http.put(constant.BASE_URI + '/task/' + id, jsonToSend);
            };

            svc.deleteTask = function (id) {
                return $http.delete(constant.BASE_URI + '/task/' + id);
            };

            return svc;
        })
})();