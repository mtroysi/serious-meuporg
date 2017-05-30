/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonSearchBarWS', function($http, constant) {
            var svc = this;

            svc.searchUsersAndTasks = function(query) {
                return $http.get(constant.BASE_URI + '/search?query=' + query);
            };

            return svc;
        })
})();