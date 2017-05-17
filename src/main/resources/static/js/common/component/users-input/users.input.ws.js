/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonUsersInputWS', function($http, constant) {
            var svc = this;

            svc.loadUsers = function(query) {
                return $http.get(constant.BASE_URI + '/user?query=' + query);
            };

            return svc;
        })
})();