/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagUpdateWS', function ($http, constant) {
            var svc = this;

            svc.deleteTag = function (id) {
                return $http.delete(constant.BASE_URI + '/tag/'+id);
            };

            return svc;
        })
})();