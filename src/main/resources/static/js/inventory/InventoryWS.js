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

            return svc;
        })
})();