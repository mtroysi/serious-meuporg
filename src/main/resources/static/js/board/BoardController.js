/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardController', function (BoardService) {
        var ctrl = this;

        ctrl.init = function () {
            ctrl.board = {};
        };

        ctrl.createBoard = function () {
            BoardService.createBoard(ctrl.board.name).then(function (data) {
                ctrl.board = data;
            })
        };
        
        ctrl.updateBoard = function () {
            
        };

        ctrl.init();
    })
})();