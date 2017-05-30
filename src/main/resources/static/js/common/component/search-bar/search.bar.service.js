/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonSearchBarService', function(CommonSearchBarWS) {
            var svc = {};

            /**
             * Retourne la liste des utilisateurs  et des tâches dont le nom contiennent la chaîne en paramètre.
             * @param query chaîne à chercher
             */
            svc.searchUsersAndTasks = function(query) {
                return CommonSearchBarWS.searchUsersAndTasks(query).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();