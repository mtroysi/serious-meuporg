/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonUsersInputService', function(CommonUsersInputWS) {
        var svc = {};

        svc.loadUsers = function(query) {
            return CommonUsersInputWS.loadUsers(query).then(function(response) {
                return response.data;
            });
        };

        return svc;
    });
})();