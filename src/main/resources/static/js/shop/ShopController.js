/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopController', function($scope, AuthenticationService, CommonDialogService, ShopService, UserService, InventoryService) {
            var ctrl = this;
            ctrl.items = [];
            ctrl.filteredItems = [];
            ctrl.user = {};
            ctrl.inventory = [];

            ctrl.init = function() {
                var id = AuthenticationService.getUserId();

                InventoryService.getInventory(id).then(function (data) {
                    ctrl.inventory = data;

                    ShopService.getAllItems().then(function(data) {
                        ctrl.items = data;
                        ctrl.filteredItems = _.filter(ctrl.items, function (item) {
                            return !(_.findWhere(ctrl.inventory, {'id':item.id}));
                        });
                    });
                });

                ctrl.user = {};
                UserService.getUser(id).then(function(response){
                    ctrl.user = response;
                });

                $scope.$watch('this.ctrl.filter.type', function() {
                    ctrl.filterItems();
                });

                $scope.$watch('this.ctrl.inventory', function() {
                    ctrl.filterItems();
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
                ctrl.itemToBuy = item;
                    CommonDialogService.confirmation('Voulez-vous acheter ' + item.name + ' ?', function() {
                        ShopService.addToInventory(ctrl.user.id, ctrl.itemToBuy).then(function (data) {
                            ctrl.inventory = data;
                        });
                    }, null, 'modalBuyItem', "Achat d'un objet", "Valider", "Annuler");
                } else {
                    CommonDialogService.error('Vous n\'avez pas le niveau ou l\'argent requis pour acheter cet objet.', 'Achat impossible', null);
                }
            };

/*            ctrl.addToInventory = function (item) {
                ShopService.addToInventory(ctrl.user.id, item).then(function (data) {
                    ctrl.inventory = data;
                })
            };*/

            ctrl.objectAlreadyBought = function (item) {
                return _.findWhere(ctrl.inventory, {'id':item.id}) !== undefined;
            };

            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            ctrl.filterItems = function () {
                ctrl.filteredItems = ctrl.items.filter(function(e) {
                    if(ctrl.filter.type === 'ACHETABLE') {
                        return ctrl.canBeBought(e);
                    } else {
                        if(e.type === 'AVATAR' || e.type === 'WALLPAPER') {
                            // return !(ctrl.objectAlreadyBought(e)) && e.type === ctrl.filter.type || !(ctrl.objectAlreadyBought(e)) && ctrl.filter.type === 'TOUT';
                            return !(ctrl.objectAlreadyBought(e)) & (e.type === ctrl.filter.type | ctrl.filter.type === 'TOUT');
                        }
                        else return e.type === ctrl.filter.type || ctrl.filter.type === 'TOUT';
                    }
                });
            };

            ctrl.init();
        })
})();