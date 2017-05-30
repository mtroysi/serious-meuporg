/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonSearchBarWS', function($http, constant) {
            var svc = this;

            /**
             * Retourne la liste des utilisateurs  et des tâches dont le nom contiennent la chaîne en paramètre.
             * @param query chaîne à chercher
             * @returns {HttpPromise}
             */
            svc.searchUsersAndTasks = function(query) {
                return $http.get(constant.BASE_URI + '/search?query=' + query);
            };

            return svc;
        })
})();