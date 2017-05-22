/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('InventoryController', function(AuthenticationService, InventoryService) {
            var ctrl = this;

            ctrl.init = function() {
                InventoryService.getInventory(Number(AuthenticationService.getUserId())).then(function (data) {
                    ctrl.inventory = data;
                })
            };

            ctrl.init();
        })
})();