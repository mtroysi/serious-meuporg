/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskUpdateWS', function ($http, constant) {
            var svc = this;

            svc.updateTask = function (id, task) {
                return $http.put(constant.BASE_URI + '/task/'+id, task);
            };

            svc.deleteTask = function (id) {
                return $http.delete(constant.BASE_URI + '/task/'+id);
            };

            return svc;
        })
})();