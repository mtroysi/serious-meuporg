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
                var idUser = AuthenticationService.getUserId();

                ShopService.getAllItemsByUserId(idUser).then(function(data) {
                    ctrl.items = data;
                    ctrl.filteredItems = angular.copy(data);
                });

                ctrl.user = {};
                UserService.getUser(idUser).then(function(response) {
                    ctrl.user = response;
                });

                ctrl.listUsers = {};
                ctrl.userSelected = {};
                UserService.getAllUser().then(function(response) {
                    ctrl.listUsers = response;
                    ctrl.userSelected = ctrl.listUsers ? ctrl.listUsers[0] : {};
                });

                $scope.$watch('this.ctrl.filter.type', function() {
                    ctrl.filterItems();
                });

            };

            ctrl.hasLevel = function(item) {
                if (ctrl.user.level >= item.requiredLevel) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            ctrl.hasMoney = function(item) {
                if (ctrl.user.money >= item.price) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            ctrl.canBeBought = function(item) {
                return (ctrl.hasLevel(item) === 'text-success') && (ctrl.hasMoney(item) === 'text-success');
            };

            ctrl.buyItem = function(item) {
                if (ctrl.canBeBought(item)) {
                    ctrl.itemSelected = item;
                    $('#modalBuyItem').modal('show');

                } else {
                    CommonDialogService.error('Vous n\'avez pas le niveau ou l\'argent requis pour acheter cet objet.', 'Achat impossible', null);
                }
            };

            ctrl.validBuyItem = function() {
                var idUserMal = ctrl.itemSelected.type === "CURSE" ? ctrl.userSelected.id : null;
                InventoryService.buyItem(ctrl.itemSelected.id, idUserMal).then(function(data) {
                    // Supprimer si c'est pas une malediction
                    if (ctrl.itemSelected.type !== "CURSE") {
                        var index = ctrl.items.findIndex(function(element) {
                            return element.id == ctrl.itemSelected.id
                        });
                        if (index !== -1) {
                            ctrl.items.splice(index, 1);
                            ctrl.filteredItems = angular.copy(ctrl.items);
                            ctrl.filterItems();
                        }
                    }
                });
            }

            ctrl.objectAlreadyBought = function(item) {
                return _.findWhere(ctrl.inventory, { 'id': item.id }) !== undefined;
            };

            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            ctrl.filterItems = function() {
                ctrl.filteredItems = ctrl.items.filter(function(e) {
                    if (ctrl.filter.type === 'ACHETABLE') {
                        return ctrl.canBeBought(e);
                    } else {
                        if (e.type === 'AVATAR' || e.type === 'WALLPAPER') {
                            // return !(ctrl.objectAlreadyBought(e)) && e.type === ctrl.filter.type || !(ctrl.objectAlreadyBought(e)) && ctrl.filter.type === 'TOUT';
                            return !(ctrl.objectAlreadyBought(e)) & (e.type === ctrl.filter.type | ctrl.filter.type === 'TOUT');
                        } else return e.type === ctrl.filter.type || ctrl.filter.type === 'TOUT';
                    }
                });
            };

            ctrl.init();
        })
})();