/**
 * Created by Morgane TROYSI on 19/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopController', function(CommonProgressService, CommonNotificationBoxService, $scope, AuthenticationService, CommonDialogService, ShopService, UserService, InventoryService) {
            var ctrl = this;
            ctrl.items = [];
            ctrl.filteredItems = [];
            ctrl.user = {};
            ctrl.inventory = [];

            ctrl.init = function() {
                var idUser = AuthenticationService.getUserId();
                ctrl.filter = { type: "TOUT" };

                // Récupère les items disponibles
                ShopService.getAllItemsByUserId(idUser).then(function(data) {
                    ctrl.items = data;
                    ctrl.filteredItems = angular.copy(data);
                });

                // Récupère l'utilisateur connecté
                ctrl.user = {};
                UserService.getUser(idUser).then(function(response) {
                    ctrl.user = response;
                });

                // Récupère la liste des utilisateurs (pour lancer malédictions)
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

            /**
             * Retourne le code couleur en fonction du niveau de l'utilisateur et du niveau requis par l'objet
             * @param item
             * @returns {string}
             */
            ctrl.hasLevel = function(item) {
                if (ctrl.user.level >= item.requiredLevel) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            /**
             * Retourne le code couleur en fonction de l'argent de l'utilisateur et le prix de l'objet
             * @param item
             * @returns {string}
             */
            ctrl.hasMoney = function(item) {
                if (ctrl.user.money >= item.price) {
                    return 'text-success';
                } else {
                    return 'text-danger';
                }
            };

            /**
             * Retourne true si l'utilisateur peut acheter l'objet
             * @param item
             * @returns {boolean}
             */
            ctrl.canBeBought = function(item) {
                return (ctrl.hasLevel(item) === 'text-success') && (ctrl.hasMoney(item) === 'text-success');
            };

            /**
             * Affiche modal d'achat ou d'erreur
             * @param item
             */
            ctrl.buyItem = function(item) {
                if (ctrl.canBeBought(item)) {
                    ctrl.itemSelected = item;
                    $('#modalBuyItem').modal('show');

                } else {
                    CommonDialogService.error('Vous n\'avez pas le niveau ou l\'argent requis pour acheter cet objet.', 'Achat impossible', null);
                }
            };

            /**
             * Achat d'un objet
             */
            ctrl.validBuyItem = function() {
                var idUserMal = ctrl.itemSelected.type === "CURSE" ? ctrl.userSelected.id : null;
                InventoryService.buyItem(ctrl.itemSelected.id, idUserMal).then(function(data) {
                    CommonNotificationBoxService.info("L'objet a été acheté", "");
                    ctrl.user.money = ctrl.user.money - ctrl.itemSelected.price;
                    CommonProgressService.setMoney(ctrl.user.money);

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
            };


            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            /**
             * Filter items
             */
            ctrl.filterItems = function() {
                ctrl.filteredItems = ctrl.items.filter(function(e) {
                    if (ctrl.filter.type === 'ACHETABLE') {
                        return ctrl.canBeBought(e);
                    } else {
                        return e.type === ctrl.filter.type || ctrl.filter.type === 'TOUT';
                    }
                });
            };

            ctrl.init();
        })
})();