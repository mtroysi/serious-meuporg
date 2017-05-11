/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskWS', function ($http, constant) {
            var svc = this;

            svc.createTask = function (name) {
                return $http.post(constant.BASE_URI + '/task/create', name);
            };

            return svc;
        })
})();