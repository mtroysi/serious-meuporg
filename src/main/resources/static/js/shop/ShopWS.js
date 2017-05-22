/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('ShopWS', function($http, constant) {
            var svc = this;

            svc.getAllItems = function() {
                return $http.get(constant.BASE_URI + '/item');
            };

            svc.addToInventory = function(idUser, item) {
                return $http.put(constant.BASE_URI + '/user/' + idUser, item);
            };

            return svc;
        })
})();