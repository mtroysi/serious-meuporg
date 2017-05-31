(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('ShopCreateController', function($scope, ShopService, CommonNotificationBoxService) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.item = { type: 'AVATAR', position: 'TOP_RIGHT', price: '0', requiredLevel: '1' };
            };

            ctrl.createItem = function() {
                ctrl.item.keyItem = ctrl.item.type + '_' + new Date().getTime();
                ShopService.createItem(ctrl.item).then(function(fetchData) {
                    //Notification de réussite
                    CommonNotificationBoxService.info("L'objet a bien été créé.", "");

                    //Réinit formulaire
                    $scope.formCreateItem.$setPristine();
                    ctrl.init();
                });
            };

            ctrl.init();
        });
})();