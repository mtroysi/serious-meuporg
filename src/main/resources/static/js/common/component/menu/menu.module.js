(function() {
    'use strict';

    function ComponentMenuController(BoardService, CommonMenuService, $scope, AuthenticationService, $state) {
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

        /**
         * Indique si le tableau est actif ou non (ligne surlignée)
         * @param arrayRoute path du tableau
         * @param idBoard id du tableau
         * @returns {string}
         */
        ctrl.getClass = function(arrayRoute, idBoard) {
            return arrayRoute.indexOf($state.current.name) > -1 && (idBoard === undefined || $state.params.idBoard == idBoard) ? 'active' : '';
        };

        ctrl.init();
    }


    angular.module('hello', ['ui.router', 'ngTagsInput', 'ngCookies', 'ui.bootstrap', 'ui.bootstrap.datetimepicker', 'toastr'])
        .component('mgMenu', {
            controller: ComponentMenuController,
            controllerAs: 'vm',
            templateUrl: 'js/common/component/menu/menu.view.html'
        });
})();