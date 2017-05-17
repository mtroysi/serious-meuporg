/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardController', function(BoardService, CommonMenuService, $state) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.board = {};
                ctrl.users = [];
            };

            ctrl.createBoard = function() {
                ctrl.board.users = angular.copy(ctrl.users);
                BoardService.createBoard(ctrl.board).then(function(data) {
                    ctrl.board = data;
                    CommonMenuService.addListBoard(angular.copy(data));
                    $state.go('app.board-preview', {id: ctrl.board.id});
                });
            };

            /* exemple usage : ctrl.updateBoard('name', ctrl.board.name) */
            ctrl.updateBoard = function(key, value) {
                var jsonToSend = {};
                value !== undefined ? jsonToSend[key] = value : jsonToSend[key] = '';
                BoardService.updateBoard(ctrl.board.id, jsonToSend).then(function(data) {
                    ctrl.board = data;
                });
            };

            ctrl.setColor = function (color) {
                ctrl.board.color = color;
            };

            ctrl.init();
        })
})();