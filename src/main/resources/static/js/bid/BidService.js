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
                var list = [];
                if (response.data) {
                    response.data.forEach(function(element) {
                        if (!(element.task.id in list)) {
                            list[element.task.id] = [];
                        }
                        list[element.task.id].push(element);
                    });
                }
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