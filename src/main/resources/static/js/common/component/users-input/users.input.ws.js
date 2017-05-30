/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonUsersInputWS', function($http, constant) {
            var svc = this;

            /**
             * Récupère les utilisateurs dont le nom contient la chaîne en paramètre
             * @param query chaîne à chercher
             * @returns {HttpPromise}
             */
            svc.loadUsers = function(query) {
                return $http.get(constant.BASE_URI + '/user?query=' + query);
            };

            return svc;
        })
})();