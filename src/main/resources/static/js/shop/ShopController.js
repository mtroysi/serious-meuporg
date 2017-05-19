/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopController', function(ShopService) {
            var ctrl = this;
            ctrl.items = [];

            ctrl.init = function() {
                ShopService.getAllItems().then(function(data) {
                    ctrl.items = data;
                });
            };

            ctrl.init();
        })
})();