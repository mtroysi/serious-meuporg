(function() {
    'use strict';

    function ComponentMenuController(BoardService) {
        var ctrl = this;

        // Constructor
        ctrl.init = function() {
            // Init variable
            ctrl.listBoard = [];

            // Call WS for get the list of board of the user
            BoardService.listBoardByUser(1).then(function(data) {
                ctrl.listBoard = data;
            });
        };

        ctrl.init();
    }


    angular.module('hello', ['ui.router']).component('mgMenu', {
        controller: ComponentMenuController,
        controllerAs: 'vm',
        templateUrl: 'js/common/component/menu/menu.view.html'
    });
})();