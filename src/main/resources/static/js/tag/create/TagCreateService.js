/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagCreateService', function (TagCreateWS) {
            var svc = {};

            svc.createTag = function (newTag) {
                return TagCreateWS.createTag(newTag).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        });
})();