/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonSearchBarService', function(CommonSearchBarWS) {
            var svc = {};

            svc.searchUsersAndTasks = function(query) {
                return CommonSearchBarWS.searchUsersAndTasks(query).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();