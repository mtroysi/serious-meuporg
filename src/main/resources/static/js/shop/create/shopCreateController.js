(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopCreateController', function(ShopService, UserService, InventoryService) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.item = { type: 'AVATAR', position: 'TOP_RIGHT', price: '0', level: '1' };
            };

            ctrl.createItem = function() {
                item.keyItem = ctrl.item.type + '_' + new Date().getTime();
            };

            ctrl.init();
        });
})();