/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryService', function(InventoryWS) {
            var svc = {};

            // svc.getBoard = function(id) {
            //     return BoardWS.getBoard(id).then(function(response) {
            //         return response.data;
            //     });
            // };

            return svc;
        });
})();