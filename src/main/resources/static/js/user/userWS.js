/**
 * Created by Sara on 18/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('UserWS', function($http, constant) {
            var svc = this;
            svc.getUser = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id);
            };
            return svc;
        })
})();