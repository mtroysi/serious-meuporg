/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagShowWS', function ($http, constant) {
            var svc = this;

            svc.showTag = function (id) {
                return $http.get(constant.BASE_URI + '/tag/'+id);
            };

            return svc;
        });
})();