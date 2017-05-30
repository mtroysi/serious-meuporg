/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardWS', function($http, constant) {
            var svc = this;

            /**
             * Retourne un tableau
             * @param id l'id du tableau à récupérer
             * @returns {HttpPromise}
             */
            svc.getBoard = function(id) {
                return $http.get(constant.BASE_URI + '/board/' + id);
            };

            /**
             * Crée un tableau
             * @param board données du tableau à créer
             * @returns {HttpPromise}
             */
            svc.createBoard = function(board) {
                return $http.post(constant.BASE_URI + '/board', board);
            };

            /**
             * Modifie un tableau
             * @param boardDto tableau modifié
             * @returns {HttpPromise}
             */
            svc.updateBoard = function(boardDto) {
                return $http.put(constant.BASE_URI + '/board/', boardDto);
            };

            /**
             * Supprime un tableau
             * @param id l'id du tableau à supprimer
             * @returns {HttpPromise}
             */
            svc.deleteBoard = function(id) {
                return $http.delete(constant.BASE_URI + '/board/' + id);
            };

            /**
             * Retourne la liste des tableaux de l'utilisateur
             * @param user_id
             * @returns {HttpPromise}
             */
            svc.listBoardByUser = function(user_id) {
                return $http.get(constant.BASE_URI + '/board/user/' + user_id);
            };

            /**
             * Crée une colonne Kanban
             * @param colonneKanban données de la colonne à créer
             * @param boardId id du tableau dans lequel créer la colonne
             * @returns {HttpPromise}
             */
            svc.createColonneKanban = function(colonneKanban, boardId) {
                return $http.post(constant.BASE_URI + '/colonneKanban/board/' + boardId, colonneKanban);
            };

            /**
             * Modifie une colonne Kanban
             * @param colonneKanban colonne modifiée
             * @returns {HttpPromise}
             */
            svc.editColonneKanban = function(colonneKanban) {
                return $http.put(constant.BASE_URI + '/colonneKanban/' + colonneKanban.id, colonneKanban);
            };

            /**
             * Supprime une colonne Kanban
             * @param colonneKanbanId id de la colonne à supprimer
             * @returns {HttpPromise}
             */
            svc.deleteColonneKanban = function(colonneKanbanId) {
                return $http.delete(constant.BASE_URI + '/colonneKanban/' + colonneKanbanId);
            };

            return svc;
        })
})();