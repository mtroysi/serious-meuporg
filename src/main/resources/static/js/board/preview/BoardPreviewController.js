(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardPreviewController', function($state, $stateParams, BoardService, $timeout) {
            var ctrl = this;

            /**
             * Constructor
             */
            ctrl.init = function() {
                ctrl.openPanelFilter = false;
                ctrl.openPanelNewColonne = false;
                ctrl.getBoard($stateParams.id);
            };

            ctrl.getBoard = function(id) {
                BoardService.getBoard(id).then(function (data) {
                    ctrl.board = data;
                });
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

            ctrl.sizeKanban = function() {
                var width = 0;
                $('.contentKanban .columnKanban').each(function() {
                    width += $(this).width() + 51;
                });
                $('.contentKanban').width(width);
            };

            ctrl.init();
        });
})();