(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('BidValidationController', function($stateParams, $location, $http, BoardService, AuthenticationService, BidService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.getBoard($stateParams.idBoard);
            ctrl.listBidEnd = [];
        };

        /**
         * Load Board
         */
        ctrl.getBoard = function(id) {
            BoardService.getBoard(id).then(function(data) {
                ctrl.board = data;
                ctrl.isAdmin = (Number(AuthenticationService.getUserId()) === ctrl.board.creator.id);

                // If admin then load tasks 
                if (ctrl.isAdmin) {
                    ctrl.getListBidEndByBoard($stateParams.idBoard);
                }
            });
        };


        ctrl.getListBidEndByBoard = function(boardId) {
            BidService.getListBidEndByBoard(boardId).then(function(fetchData) {
                ctrl.listBidEnd = fetchData;
            });
        }
        ctrl.init();
    })
})();