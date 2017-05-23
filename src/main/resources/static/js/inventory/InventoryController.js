/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('InventoryController', function($scope, AuthenticationService, InventoryService, CommonDialogService) {
            var ctrl = this;
            ctrl.inventory = [];

            ctrl.init = function() {
                InventoryService.getInventory(Number(AuthenticationService.getUserId())).then(function (data) {
                    ctrl.inventory = data;
                    ctrl.filteredInventory = data;
                });
                $scope.$watch('this.ctrl.filter.type', function() {
                    ctrl.filterItems();
                });
            };

            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            ctrl.filterItems = function () {
                ctrl.filteredInventory = ctrl.inventory.filter(function(e) {
                    return e.type === ctrl.filter.type || ctrl.filter.type === 'TOUT';
                });
            };

            ctrl.removeFromInventory = function (item) {
                ctrl.itemToRemove = item;

                ctrl.inventory = _.reject(ctrl.inventory, function (e) {
                    return e.id === item.id;
                });

                CommonDialogService.confirmation('Êtes-vous sûr de vouloir jeter ' + item.name + ' ? Cette action est irréversible.', function () {
                    InventoryService.updateInventory(ctrl.inventory).then(function (data) {
                        ctrl.inventory = data;
                        ctrl.filteredInventory = data;
                    });
                }, function () {
                    ctrl.inventory.push(ctrl.itemToRemove);
                }, 'modalThrowItem', "Jeter un objet", "Valider", "Annuler");




            };

            ctrl.init();
        })
})();