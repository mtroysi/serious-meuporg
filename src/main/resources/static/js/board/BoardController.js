/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardController', function(BoardService, CommonMenuService, $state, $stateParams) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.board = {};
                ctrl.users = [];
            };

            ctrl.createBoard = function() {
                // console.log(ctrl.board);
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

            ctrl.deleteBoard = function() {
                BoardService.deleteBoard(ctrl.board.id).then(function() {
                    // redirection, rafraichissement de la page ?
                    $state.go('app.dashboard');
                });
            };

            ctrl.setColor = function (color) {
                ctrl.board.color = color;
            };

            ctrl.init();
        })
})();