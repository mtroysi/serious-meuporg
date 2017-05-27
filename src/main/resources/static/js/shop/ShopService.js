/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('ShopService', function(ShopWS) {
            var svc = {};

            svc.getAllItemsByUserId = function(idUser) {
                return ShopWS.getAllItemsByUserId(idUser).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        });
})();