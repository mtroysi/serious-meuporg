/**
 * Created by etudiant on 24/05/17.
 */
(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagListService', function(TagListWS) {
            var svc = {};

            svc.listTags = function() {
                return TagListWS.listTags().then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();