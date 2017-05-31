/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('ShopWS', function($http, constant) {
            var svc = this;

            /**
             * Retourne tous les objets non présents actuellement dans l'inventaire du joueur
             * @param idUser id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.getAllItemsByUserId = function(idUser) {
                return $http.get(constant.BASE_URI + '/item/' + idUser);
            };

            /**
             * Créé un nouveau item
             * @param itemDTO itemDTO
             * @returns {HttpPromise}
             */
            svc.createItem = function(itemDTO) {
                return $http.post(constant.BASE_URI + '/item/', itemDTO);
            };

            return svc;
        })
})();