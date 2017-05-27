/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryWS', function($http, constant) {
            var svc = this;

            svc.getInventory = function(id) {
                return $http.get(constant.BASE_URI + '/inventory/' + id);
            };

            svc.updateInventory = function(inventory) {
                return $http.put(constant.BASE_URI + '/inventory/', inventory);
            };

            svc.removeItem = function(idItem) {
                return $http.delete(constant.BASE_URI + '/inventory/' + idItem);
            };

            svc.activeItem = function(idItem, active) {
                return $http.put(constant.BASE_URI + '/inventory/' + idItem + "/active", active);
            };

            svc.buyItem = function(idItem, idUser) {
                return $http.post(constant.BASE_URI + '/inventory/' + idItem, idUser);
            };

            return svc;
        })
})();