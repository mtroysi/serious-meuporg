/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskWS', function($http, constant) {
            var svc = this;

            svc.createTask = function(name) {
                return $http.post(constant.BASE_URI + '/task/create', name);
            };

            svc.updateTask = function(id, jsonToSend) {
                return $http.put(constant.BASE_URI + '/task/' + id, jsonToSend);
            };

            svc.updateColumnTask = function(taskUserId, idColumn) {
                return $http.put(constant.BASE_URI + '/taskUser/' + taskUserId + '/column', idColumn);
            };

            svc.updatePriorityTask = function(taskUserId, priority) {
                return $http.put(constant.BASE_URI + '/taskUser/' + taskUserId + '/priority', priority);
            };

            svc.deleteTask = function(id) {
                return $http.delete(constant.BASE_URI + '/taskUser/' + id);
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