/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('ShopService', function(ShopWS) {
            var svc = {};

            /**
             * Retourne tous les objets non présents actuellement dans l'inventaire du joueur
             * @param idUser id de l'utilisateur
             */
            svc.getAllItemsByUserId = function(idUser) {
                return ShopWS.getAllItemsByUserId(idUser).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Créé un nouveau item
             * @param itemDTO item a créer
             */
            svc.createItem = function(itemDTO) {
                return ShopWS.createItem(itemDTO).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();