/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardService', function(BoardWS) {
            var svc = {};

            /**
             * Retourne un tableau
             * @param id l'id du tableau à récupérer
             */
            svc.getBoard = function(id) {
                return BoardWS.getBoard(id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Crée un tableau
             * @param board données du tableau à créer
             */
            svc.createBoard = function(board) {
                return BoardWS.createBoard(board).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retourne la liste des tableaux de l'utilisateur
             * @param user_id
             */
            svc.listBoardByUser = function(user_id) {
                return BoardWS.listBoardByUser(user_id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Modifie un tableau
             * @param board tableau modifié
             */
            svc.updateBoard = function(board) {
                return BoardWS.updateBoard(board).then(function(response) {
                    return response.data;
                })
            };

            /**
             * Supprime un tableau
             * @param id l'id du tableau à supprimer
             */
            svc.deleteBoard = function(id) {
                return BoardWS.deleteBoard(id);
            };

            /**
             * Crée une colonne Kanban
             * @param colonneKanban données de la colonne à créer
             * @param boardId id du tableau dans lequel créer la colonne
             */
            svc.createColonneKanban = function(colonneKanban, boardId) {
                return BoardWS.createColonneKanban(colonneKanban, boardId).then(function(response) {
                    return response.data;
                })
            };

            /**
             * Modifie une colonne Kanban
             * @param colonneKanban colonne modifiée
             */
            svc.editColonneKanban = function(colonneKanban) {
                return BoardWS.editColonneKanban(colonneKanban).then(function(response) {
                    return response.data;
                })
            };

            /**
             * Supprime une colonne Kanban
             * @param idColonneKanban id de la colonne à supprimer
             */
            svc.deleteColonneKanban = function(idColonneKanban) {
                return BoardWS.deleteColonneKanban(idColonneKanban).then(function(response) {
                    return response.data;
                })
            };

            /**
             * Retourne le tableau à partir de l'id d'une tâche
             * @param idTask id de la tâche
             */
            svc.getBoardFromTask = function(idTask) {
                return BoardWS.getBoardFromTask(idTask).then(function(response) {
                    return response.data;
                })
            };

            return svc;
        });
})();