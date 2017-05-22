/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('InventoryWS', function($http, constant) {
            var svc = this;

            // svc.getBoard = function(id) {
            //     return $http.get(constant.BASE_URI + '/board/' + id);
            // };

            return svc;
        })
})();