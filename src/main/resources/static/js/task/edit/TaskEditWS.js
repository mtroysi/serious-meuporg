/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskEditWS', function ($http, constant) {
            var svc = this;

            svc.editTask = function (id, task) {
                console.log(task);
                return $http.put(constant.BASE_URI + '/task/'+id, task);
            };


            return svc;
        })
})();