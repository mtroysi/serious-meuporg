/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonUsersInputService', function (CommonUsersInputWS) {
            var svc = {};

            /**
             * Récupère les utilisateurs dont le nom contient la chaîne en paramètre
             * @param query chaîne à chercher
             * @param withCurrent paramètre pour spécifier si on inclut l'utilisateur actuel
             * @param boardId id du tableau
             * @returns {HttpPromise}
             */
            svc.loadUsers = function (query, withCurrent, boardId) {
                return CommonUsersInputWS.loadUsers(query, withCurrent, boardId).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        });
})();