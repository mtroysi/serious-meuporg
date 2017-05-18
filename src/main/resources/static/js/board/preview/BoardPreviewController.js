(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardPreviewController', function($state, $stateParams, BoardService, $timeout, CommonMenuService, TaskService, AuthenticationService) {
            var ctrl = this;

            /**
             * Constructor
             */
            ctrl.init = function() {
                ctrl.openPanelFilter = false;
                ctrl.openPanelNewColonne = false;
                ctrl.typeDisplayTeam = false;
                ctrl.listTask = [];
                ctrl.getBoard($stateParams.id);
                ctrl.getTaskBoard($stateParams.id, AuthenticationService.getUserId());
            };

            /**
             * WS Loard info board
             */
            ctrl.getBoard = function(id) {
                BoardService.getBoard(id).then(function(data) {
                    ctrl.board = data;
                });
            };

            /**
             * WS Loard list of task by board and/or user
             */
            ctrl.getTaskBoard = function(board_id, user_id) {
                // Task of the team 
                if (ctrl.typeDisplayTeam === true) {
                    TaskService.listTaskByBoard(board_id).then(function(fetchData) {
                        console.log('rrrrrrr');
                        ctrl.listTask = fetchData;
                    });
                } else {
                    TaskService.listTaskByBoardAndUser(board_id, user_id).then(function(fetchData) {
                        console.log('rrrrr22rr');
                        ctrl.listTask = fetchData;
                    });
                }
            };

            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            /**
             * Open panel Task (Kanban)
             */
            ctrl.zoomTask = function(type) {
                $($('.boxMatrice .panelMatrice').get().reverse()).each(function(index) {
                    $(this).fadeToggle(150);
                });
                $timeout(function() { $('.bigPanelMatrice').fadeToggle(); }, 200);
                ctrl.sizeKanban();
            };

            /**
             * Box size management
             */
            ctrl.sizeKanban = function() {
                var width = 0;
                $('.contentKanban .columnKanban').each(function() {
                    width += $(this).width() + 51;
                });
                $('.contentKanban').width(width);
            };

            /**
             * Delete Board
             */
            ctrl.deleteBoard = function() {
                BoardService.deleteBoard(ctrl.board.id).then(function() {
                    CommonMenuService.removeListBoard(ctrl.board.id);
                    $state.go('app.dashboard');
                });
            };

            ctrl.init();
        });
})();