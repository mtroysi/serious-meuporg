/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryService', function(InventoryWS) {
            var svc = {};

            svc.getInventory = function(id) {
                return InventoryWS.getInventory(id).then(function(response) {
                    return response.data;
                });
            };

            svc.updateInventory = function(inventory) {
                return InventoryWS.updateInventory(inventory).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();