/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskService', function(TaskWS) {
            var svc = {};

            svc.createTask = function(name) {
                return TaskWS.createTask(name).then(function(response) {
                    return response.data;
                });
            };

            svc.updateTask = function(id, jsonToSend) {
                return TaskWS.updateTask(id, jsonToSend).then(function(response) {
                    return response.data;
                });
            };

            svc.deleteTask = function(id) {
                return TaskWS.deleteTask(id).then(function(response) {
                    return response.data;
                });
            };

            svc.listTaskByBoardAndUser = function(board_id, user_id) {
                return TaskWS.listTaskByBoardAndUser(board_id, user_id).then(function(response) {
                    return response.data;
                });
            };

            svc.listTaskByBoard = function(board_id) {
                return TaskWS.listTaskByBoard(board_id).then(function(response) {
                    return response.data;
                });
            };

            svc.listTaskByUser = function(user_id) {
                return TaskWS.listTaskByUser(user_id).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        })
})();