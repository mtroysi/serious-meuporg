(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('BidWS', function($http, constant) {
        var svc = this;


        svc.getListBidByBoardAndUser = function(boardId, userId) {
            return $http.get(constant.BASE_URI + '/bid/board/' + boardId + '/user/' + userId);
        };

        svc.getListBidEndByBoard = function(boardId) {
            return $http.get(constant.BASE_URI + '/bid/end/board/' + boardId);
        };

        svc.addOrUpdateTaskUserBid = function(taskId, duration) {
            return $http.post(constant.BASE_URI + '/bid/task/' + taskId, duration);
        };

        svc.addBid = function(date, listTaskId) {
            return $http.post(constant.BASE_URI + '/bid/?dateend=' + date, listTaskId);
        };

        return svc;
    })
})();