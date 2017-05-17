/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagShowService', function (TagShowWS) {
            var svc = {};

            svc.showTag = function (id) {
                return TagShowWS.showTag(id).then(function (response) {
                    return response.data;
                });
            };


            return svc;
        })
})();