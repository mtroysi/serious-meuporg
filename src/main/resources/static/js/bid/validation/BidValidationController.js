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


        ctrl.validAffectation = function() {
            var listFinal = [];
            $.each(ctrl.listBidEnd, function(index, value) {
                // returns the id list when the state is true
                var listUser = [];
                if (value.listTask) {
                    value.listTask.forEach(function(element) {
                        if (element.etat === true) {
                            listUser.push(element.userId);
                        }
                    });
                }

                listFinal.push({ idTask: value.task.id, duration: value.value, listUserId: listUser });
            });

            BidService.validBid($stateParams.idBoard, listFinal);
        }


        ctrl.init();
    })
})();