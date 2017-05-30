/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryWS', function($http, constant) {
            var svc = this;

            /**
             * Retourne l'inventaire d'un utilisateur
             * @param id l'id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.getInventory = function(id) {
                return $http.get(constant.BASE_URI + '/inventory/' + id);
            };

            /**
             * Modifie l'inventaire d'un utilisateur
             * @param inventory inventaire modifié
             * @returns {HttpPromise}
             */
            svc.updateInventory = function(inventory) {
                return $http.put(constant.BASE_URI + '/inventory/', inventory);
            };

            /**
             * Retire un objet de l'inventaire
             * @param idItem id de l'objet à retirer
             * @returns {HttpPromise}
             */
            svc.removeItem = function(idItem) {
                return $http.delete(constant.BASE_URI + '/inventory/' + idItem);
            };

            /**
             * Active ou désactive un objet de l'inventaire
             * @param idItem id de l'objet
             * @param active {boolean}
             * @returns {HttpPromise}
             */
            svc.activeItem = function(idItem, active) {
                return $http.put(constant.BASE_URI + '/inventory/' + idItem + "/active", active);
            };

            /**
             * Achète un objet
             * @param idItem id de l'objet
             * @param idUser id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.buyItem = function(idItem, idUser) {
                return $http.post(constant.BASE_URI + '/inventory/' + idItem, idUser);
            };

            return svc;
        })
})();