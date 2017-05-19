/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopController', function(AuthenticationService, ShopService, UserService) {
            var ctrl = this;
            ctrl.items = [];
            ctrl.user = {};

            ctrl.init = function() {
                ShopService.getAllItems().then(function(data) {
                    ctrl.items = data;
                });

                ctrl.user = {};
                var id = AuthenticationService.getUserId();
                UserService.getUser(id).then(function(response){
                    ctrl.user = response;
                });
            };

            ctrl.hasLevel = function (item) {
                if(ctrl.user.level >= item.requiredLevel) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            ctrl.hasMoney = function (item) {
                if(ctrl.user.money >= item.price) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            ctrl.init();
        })
})();