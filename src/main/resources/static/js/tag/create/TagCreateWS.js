/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagCreateWS', function ($http, constant) {
            var svc = this;

            svc.createTag = function (newTag) {
                return $http.post(constant.BASE_URI + '/tag/', newTag);
            };

            return svc;
        })
})();