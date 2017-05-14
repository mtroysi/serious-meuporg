/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowService', function (TaskShowWS) {
            var svc = {};

            svc.showTask = function (id) {
                return TaskShowWS.showTask(id).then(function (response) {
                    return response.data;
                });
            };


            return svc;
        })
})();