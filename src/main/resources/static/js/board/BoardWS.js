/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardWS', function($http, constant) {
            var svc = this;

            svc.getBoard = function(id) {
                return $http.get(constant.BASE_URI + '/board/' + id);
            };

            svc.createBoard = function(board) {
                return $http.post(constant.BASE_URI + '/board', board);
            };

            svc.updateBoard = function(id, jsonToSend) {
                return $http.put(constant.BASE_URI + '/board/' + id, jsonToSend);
            };

            svc.deleteBoard = function(id) {
                return $http.delete(constant.BASE_URI + '/board/' + id);
            };

            svc.listBoardByUser = function(user_id) {
                return $http.get(constant.BASE_URI + '/board/user/' + user_id);
            };

            svc.createColonneKanban = function(colonneKanban, boardId) {
                return $http.post(constant.BASE_URI + '/colonneKanban/board/' + boardId, colonneKanban);
            };

            svc.editColonneKanban = function(colonneKanban) {
                return $http.put(constant.BASE_URI + '/colonneKanban/' + colonneKanban.id, colonneKanban);
            };

            return svc;
        })
})();