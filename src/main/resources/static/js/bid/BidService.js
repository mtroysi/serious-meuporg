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

        return svc;
    })
})();