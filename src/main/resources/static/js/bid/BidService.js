(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('BidService', function(BidWS) {
        var svc = {};

        svc.getListBidByBoardAndUser = function(boardId, userId) {
            return BidWS.getListBidByBoardAndUser(boardId, userId).then(function(response) {
                return response.data;
            });
        };

        svc.addOrUpdateTaskUserBid = function(taskId, duration) {
            return BidWS.addOrUpdateTaskUserBid(taskId, duration).then(function(response) {
                return response.data;
            });
        };

        svc.getListBidEndByBoard = function(boardId) {
            return BidWS.getListBidEndByBoard(boardId).then(function(response) {
                var list = {};
                if (response.data) {
                    response.data.forEach(function(element) {
                        if (!("task_" + element.task.id in list)) {
                            list["task_" + element.task.id] = { task: element.task, value: element.task.duration, listTask: [] };
                        }
                        list["task_" + element.task.id].listTask.push({
                            "userId": element.user.id,
                            "userName": element.user.lastName + ' ' + element.user.firstName,
                            "duration": element.duration,
                            "etat": false,
                            "value": 0
                        });
                    });
                }
                console.log(list);
                return list;
            });
        };

        svc.addBid = function(date, listTaskId) {
            return BidWS.addBid(date, listTaskId).then(function(response) {
                return response.data;
            });
        };

        return svc;
    })
})();