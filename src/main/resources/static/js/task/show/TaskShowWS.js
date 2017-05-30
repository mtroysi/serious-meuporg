/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowWS', function ($http, constant) {
            var svc = this;

            svc.showTask = function (id) {
                return $http.get(constant.BASE_URI + '/task/'+id);
            };

            svc.addComment = function (id, comment) {
                return $http.post(constant.BASE_URI + '/task/'+id, comment);
            };

            svc.deleteComment = function(id) {
                return $http.delete(constant.BASE_URI + '/comment/'+id);
            };

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

            return svc;
        })
})();