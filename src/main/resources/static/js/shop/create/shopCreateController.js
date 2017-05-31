(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopCreateController', function(ShopService, UserService, InventoryService) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.item = { type: 'AVATAR', position: 'TOP_RIGHT' };
            };

            ctrl.createItem = function() {
                alert('rrr');
            };

            ctrl.init();
        });
})();