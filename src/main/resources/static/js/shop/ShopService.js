/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('ShopService', function(ShopWS) {
            var svc = {};

            svc.getAllItems = function() {
                return ShopWS.getAllItems().then(function(response) {
                    return response.data;
                });
            };

            svc.addToInventory = function(idUser, item) {
                return ShopWS.addToInventory(idUser, item).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();