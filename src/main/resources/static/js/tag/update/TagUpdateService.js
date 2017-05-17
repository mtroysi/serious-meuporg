/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagUpdateService', function (TagUpdateWS) {
            var svc = {};

            svc.updateTag = function (id, tag) {
                return TagUpdateWS.updateTask(id, tag).then(function (response) {
                    return response.data;
                });
            };

            svc.deleteTag = function (id) {
                return TagUpdateWS.deleteTag(id).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        });
})();