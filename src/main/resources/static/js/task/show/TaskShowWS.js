/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowWS', function ($http, constant) {
            var svc = this;

            svc.showTask = function (id) {
                return $http.get(constant.BASE_URI + '/task/'+id);
            };


            return svc;
        })
})();