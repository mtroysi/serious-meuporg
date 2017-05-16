(function () {
    'use strict';

    function ComponentMenuController(BoardService, CommonMenuService, $scope) {
        var ctrl = this;

        // Constructor
        ctrl.init = function () {
            // Init variable
            ctrl.listBoard = [];
            ctrl.CommonMenuService = CommonMenuService;

            // Call WS for get the list of board of the user
            // TODO : METTRE LE VRAI ID
            BoardService.listBoardByUser(1).then(function (data) {
                CommonMenuService.initListBoard(angular.copy(data));
            });

            /**
             * Watch the list of menu (list of board)
             * Variable to watch : listBoard
             */
            $scope.$watch('this.vm.CommonMenuService.listBoard', function (newValues) {
                ctrl.listBoard = newValues;
            });
        };

        ctrl.init();
    }


    angular.module('hello', ['ui.router', 'ngTagsInput'])
        .component('mgMenu', {
            controller: ComponentMenuController,
            controllerAs: 'vm',
            templateUrl: 'js/common/component/menu/menu.view.html'
        });
})();