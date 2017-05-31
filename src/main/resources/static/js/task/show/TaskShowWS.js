/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowWS', function ($http, constant) {
            var svc = this;

            /**
             * Retourne une tâche
             * @param id tâche à retourner
             * @returns {HttpPromise}
             */
            svc.showTask = function (id) {
                return $http.get(constant.BASE_URI + '/task/'+id);
            };

            /**
             * Ajoute un commentaire à une tâche
             * @param id de la tâche
             * @param comment données du commentaire à ajouter
             * @returns {HttpPromise}
             */
            svc.addComment = function (id, comment) {
                return $http.post(constant.BASE_URI + '/task/'+id, comment);
            };

            /**
             * Supprime un commentaire
             * @param id du commentaire à supprimer
             * @returns {HttpPromise}
             */
            svc.deleteComment = function(id) {
                return $http.delete(constant.BASE_URI + '/comment/'+id);
            };

            /**
             * Modifie un commentaire
             * @param id du commentaire
             * @param comment données modifiées
             * @returns {HttpPromise}
             */
            svc.updateComment = function(id, comment) {
                return $http.put(constant.BASE_URI + '/comment/'+id, comment);
            };

            svc.listTaskByBoardAndUser = function(board_id, user_id) {
                return $http.get(constant.BASE_URI + '/taskUser/board/' + board_id + '/user/' + user_id);
            };

            svc.listTaskByBoard = function(board_id) {
                return $http.get(constant.BASE_URI + '/taskUser/board/' + board_id);
            };

            svc.listTaskByUser = function(user_id) {
                return $http.get(constant.BASE_URI + '/taskUser/user/' + user_id);
            };

            svc.getTaskWithoutUser = function(boardId) {
                return $http.get(constant.BASE_URI + '/bid/withoutuser/board/' + boardId);
            };

            svc.listTaskByUserAndBoard = function(user_id){
                return $http.get(constant.BASE_URI + '/task/usertaskbid/' + user_id);
            }

            return svc;
        })
})();