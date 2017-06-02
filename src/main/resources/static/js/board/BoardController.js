/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardController', function(BoardService, CommonMenuService, $state, $stateParams) {
            var ctrl = this;

            /**
             * Initialise le controller.
             * Si un id est fourni en paramètres, récupère les informations du tableau.
             */
            ctrl.init = function() {
                if (!$stateParams.idBoard) {
                    ctrl.board = {};
                    ctrl.board.users = [];
                } else {
                    BoardService.getBoard($stateParams.idBoard).then(function(data) {
                        ctrl.board = data;
                        //On ne veut pas que le créateur(= admin) du tableau n'apparaisse dans les utilisateurs invités.
                        //A voir si ça pose pb plus tard ?
                        ctrl.board.users = _.filter(ctrl.board.users, function(user) {
                            return user.id !== ctrl.board.creator.id;
                        })
                    });
                }
            };

            /**
             * Crée un tableau.
             */
            ctrl.createBoard = function() {
                // ctrl.board.users = angular.copy(ctrl.users);
                BoardService.createBoard(ctrl.board).then(function(data) {
                    data.role = 'ADMIN';
                    ctrl.board = data;
                    CommonMenuService.addListBoard(angular.copy(data));
                    $state.go('app.board-preview', { idBoard: ctrl.board.id });
                });
            };

            /* exemple usage : ctrl.updateBoard('name', ctrl.board.name) */
            /*ctrl.updateBoard = function(key, value) {
                var jsonToSend = {};
                value !== undefined ? jsonToSend[key] = value : jsonToSend[key] = '';
                BoardService.updateBoard(ctrl.board.id, jsonToSend).then(function(data) {
                    ctrl.board = data;
                });
            };*/

            /**
             * Modifie le tableau courant.
             */
            ctrl.updateBoard = function() {
                BoardService.updateBoard(ctrl.board).then(function(data) {
                    CommonMenuService.updateElementListBoard(angular.copy(ctrl.board));
                    $state.go('app.board-preview', { idBoard: data.id });
                });
            };

            ctrl.init();
        })
})();