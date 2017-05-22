/**
 * Created by Morgane TROYSI on 22/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('InventoryController', function($scope, AuthenticationService, InventoryService) {
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

            ctrl.init();
        })
})();