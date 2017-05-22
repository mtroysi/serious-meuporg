/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopController', function(AuthenticationService, CommonDialogService, ShopService, UserService) {
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

            ctrl.canBeBought = function (item) {
                return (ctrl.hasLevel(item)==='text-success') && (ctrl.hasMoney(item)==='text-success');
            };

            ctrl.buyItem = function (item) {
                if(ctrl.canBeBought(item)) {
                    CommonDialogService.confirmation('Voulez-vous acheter ' + item.name + ' ?', null, null, 'modalBuyItem', "Achat d'un objet", "Valider", "Annuler");
                } else {
                    CommonDialogService.error('Vous n\'avez pas le niveau ou l\'argent requis pour acheter cet objet.', 'Achat impossible', null);
                }
            };

            ctrl.init();
        })
})();