/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowService', function (TaskShowWS) {
            var svc = {};

            /**
             * Retourne une tâche
             * @param id tâche à retourner
             */
            svc.showTask = function (id) {
                return TaskShowWS.showTask(id).then(function (response) {
                    return response.data;
                });
            };

            /**
             * Ajoute un commentaire à une tâche
             * @param id de la tâche
             * @param comment données du commentaire à ajouter
             */
            svc.addComment = function (id, comment, creator) {
                comment.creator = creator;
                return TaskShowWS.addComment(id, comment).then(function (response) {
                    return response.data;
                });
            };

            /**
             * Supprime un commentaire
             * @param id du commentaire à supprimer
             */
            svc.deleteComment = function (id) {
                return TaskShowWS.deleteComment(id).then(function (response) {
                    return response.data;
                });
            };

            /**
             * Modifie un commentaire
             * @param id du commentaire
             * @param comment données modifiées
             */
            svc.updateComment = function (id, comment) {
                return TaskShowWS.updateComment(id, comment).then(function (response) {
                    return response.data;
                });
            };
            svc.listTaskByBoardAndUser = function(board_id, user_id) {
                return TaskShowWS.listTaskByBoardAndUser(board_id, user_id).then(function(response) {
                    return response.data;
                });
            };

            svc.listTaskByBoard = function(board_id) {
                return TaskShowWS.listTaskByBoard(board_id).then(function(response) {
                    return response.data;
                });
            };

            svc.listTaskByUser = function(user_id) {
                return TaskShowWS.listTaskByUser(user_id).then(function(response) {
                    return response.data;
                });
            };

            svc.getTaskWithoutUser = function(boardId) {
                return TaskShowWS.getTaskWithoutUser(boardId).then(function(response) {
                    return response.data;
                });
            };


            return svc;
        })
})();