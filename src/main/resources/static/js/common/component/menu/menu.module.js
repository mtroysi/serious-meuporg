(function() {
    'use strict';

    function ComponentMenuController(BoardService, CommonMenuService, $scope, AuthenticationService) {
        var ctrl = this;

        // Constructor
        ctrl.init = function() {
            // Init variable
            ctrl.listBoard = [];
            ctrl.CommonMenuService = CommonMenuService;

            // Call WS for get the list of board of the user
            BoardService.listBoardByUser(AuthenticationService.getUserId()).then(function(data) {
                CommonMenuService.initListBoard(angular.copy(data));
            });

            /**
             * Watch the list of menu (list of board)
             * Variable to watch : listBoard
             */
            $scope.$watch('this.vm.CommonMenuService.listBoard', function(newValues) {
                ctrl.listBoard = newValues;
            });
        };

        ctrl.init();
    }


    angular.module('hello', ['ui.router', 'ngTagsInput', 'ngCookies', 'ui.bootstrap', 'ui.bootstrap.datetimepicker'])
        .component('mgMenu', {
            controller: ComponentMenuController,
            controllerAs: 'vm',
            templateUrl: 'js/common/component/menu/menu.view.html'
        });
})();