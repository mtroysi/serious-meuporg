/**
 * Created by sara on 18/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('UserService', function(UserWS) {
            var svc = {};

            svc.getUser = function(id) {
                return UserWS.getUser(id).then(function(response) {
                    return response.data;
                });
            };
            return svc;
        });
})();