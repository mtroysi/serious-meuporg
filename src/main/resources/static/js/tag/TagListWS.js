/**
 * Created by etudiant on 24/05/17.
 */
(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TagListWS', function($http, constant) {
            var svc = this;

            svc.listTags = function() {
                return $http.get(constant.BASE_URI + '/tag');
            };

            return svc;
        })
})();