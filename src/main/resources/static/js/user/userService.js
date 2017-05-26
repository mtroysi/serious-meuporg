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

            svc.getAllUser = function() {
                return UserWS.getAllUser().then(function(response) {
                    return response.data;
                });
            };

            svc.editUser = function(user) {
                return UserWS.editUser(user).then(function(response) {
                    return response.data;
                });
            };

            svc.getStats = function(id) {
                return UserWS.getStats(id).then(function(response) {
                    return response.data;
                });
            }

            svc.getRankin = function(id) {
                return UserWS.getRankin(id).then(function(response) {
                    return response.data;
                });
            }
            return svc;
        });
})();