/**
 * Created by Morgane TROYSI on 11/05/17.
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

            return svc;
        })
})();