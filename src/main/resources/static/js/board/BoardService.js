/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardService', function(BoardWS) {
            var svc = {};

            svc.getBoard = function(id) {
                return BoardWS.getBoard(id).then(function(response) {
                    return response.data;
                });
            };

            svc.createBoard = function(board) {
                return BoardWS.createBoard(board).then(function(response) {
                    return response.data;
                });
            };

            svc.listBoardByUser = function(user_id) {
                return BoardWS.listBoardByUser(user_id).then(function(response) {
                    return response.data;
                });
            };

            svc.updateBoard = function(id, jsonToSend) {
                return BoardWS.updateBoard(id, jsonToSend).then(function(response) {
                    return response.data;
                })
            };

            svc.deleteBoard = function(id) {
                return BoardWS.deleteBoard(id);
            };

            svc.createColonneKanban = function(colonneKanban) {
                return BoardWS.createColonneKanban(colonneKanban).then(function(response) {
                    return response.data;
                })
            };

            svc.editColonneKanban = function(colonneKanban) {
                return BoardWS.editColonneKanban(colonneKanban).then(function(response) {
                    return response.data;
                })
            };

            return svc;
        });
})();