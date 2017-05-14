/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardController', function(BoardService, CommonMenuService) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.board = {};
                ctrl.board.id = 2;
            };

            ctrl.createBoard = function() {
                BoardService.createBoard(ctrl.board.name).then(function(data) {
                    ctrl.board = data;
                    CommonMenuService.addListBoard(angular.copy(data));
                });
            };

            /* exemple usage : ctrl.updateBoard('name', ctrl.board.name) */
            ctrl.updateBoard = function(key, value) {
                var jsonToSend = {};
                value !== undefined ? jsonToSend[key] = value : jsonToSend[key] = '';
                BoardService.updateBoard(ctrl.board.id, jsonToSend).then(function(data) {
                    ctrl.board = data;
                })
            };

            ctrl.deleteBoard = function() {
                BoardService.deleteBoard(ctrl.board.id).then(function() {
                    // redirection, rafraichissement de la page ?
                })
            };

            ctrl.init();
        })
})();