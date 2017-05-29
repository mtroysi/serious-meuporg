/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryService', function(InventoryWS) {
            var svc = {};

            /**
             * Retourne l'inventaire d'un utilisateur
             * @param id l'id de l'utilisateur
             */
            svc.getInventory = function(id) {
                return InventoryWS.getInventory(id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Modifie l'inventaire d'un utilisateur
             * @param inventory inventaire modifié
             */
            svc.updateInventory = function(inventory) {
                return InventoryWS.updateInventory(inventory).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retire un objet de l'inventaire
             * @param idItem id de l'objet à retirer
             */
            svc.removeItem = function(idItem) {
                return InventoryWS.removeItem(idItem).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Active ou désactive un item
             * @param idItem id de l'item
             * @param active {boolean}
             */
            svc.activeItem = function(idItem, active) {
                return InventoryWS.activeItem(idItem, active).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Achète un objet
             * @param idItem id de l'objet
             * @param idUser id de l'utilisateur
             */
            svc.buyItem = function(idItem, idUser) {
                return InventoryWS.buyItem(idItem, idUser).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();