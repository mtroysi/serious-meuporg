(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('BidPreviewController', function(moment, $location, $http, BoardService, $stateParams, AuthenticationService, TaskService, BidService) {
        var ctrl = this;

        /**
         * Initialisation
         */
        ctrl.init = function() {
            ctrl.initCarousel();
            ctrl.getBoard($stateParams.idBoard);
            ctrl.getListBidByBoardAndUser($stateParams.idBoard, AuthenticationService.getUserId());
            ctrl.date = null;
            // Admin
            ctrl.listTaskWithoutUserSelected = [];
            ctrl.listTaskWithoutUser = [];
            ctrl.dateAdmin = moment().add(1, 'day');
            // User
            ctrl.taskShow = {};
            ctrl.listTaskBid = [];
        };


        /**
         * Load Board
         */
        ctrl.getBoard = function(id) {
            BoardService.getBoard(id).then(function(data) {
                ctrl.board = data;
                ctrl.isAdmin = (Number(AuthenticationService.getUserId()) === ctrl.board.creator.id);

                // If admin then load tasks without user
                if (ctrl.isAdmin) {
                    ctrl.getTaskWithoutUser(id);
                }
            });
        };

        /**
         * Load TaskUserBid By Board and User
         */
        ctrl.getListBidByBoardAndUser = function(boardId, userId) {
            BidService.getListBidByBoardAndUser(boardId, userId).then(function(data) {
                ctrl.listTaskBid = data || [];
                ctrl.showTaskCarouselAndTinder(true);
            });
        };

        /**
         * Display task carousel
         * @param first
         */
        ctrl.showTaskCarouselAndTinder = function(first) {
            if (ctrl.listTaskBid.length > 0) {
                if (first) {
                    ctrl.taskShow = ctrl.listTaskBid[0];
                    // Refresh carousel
                    setTimeout(function() {
                        $('.responsive-carousel')[0].slick.refresh();
                    }, 10);
                } else {
                    ctrl.taskShow = ctrl.listTaskBid.find(function(element) {
                        return !("read" in element);
                    });
                }
            }
        };

        /**
         * Change task
         */
        ctrl.changeTask = function(task) {
            ctrl.taskShow = task;
        };

        /**
         * Accepted task
         */
        ctrl.acceptedTask = function() {
            $('#editDurationBid').modal('show');
            ctrl.durationBid = ctrl.taskShow.duration;
        };

        /**
         * refused task
         */
        ctrl.refusedTask = function() {
            ctrl.taskShow.read = true;
            ctrl.showTaskCarouselAndTinder();
        };



        /**
         * Load Task without user (admin only)
         */
        ctrl.getTaskWithoutUser = function(idBoard) {
            TaskService.getTaskWithoutUser(idBoard).then(function(data) {
                ctrl.listTaskWithoutUser = data;
            });
        };

        /**
         * Init carousel responsive
         */
        ctrl.initCarousel = function() {
            $('.responsive-carousel').slick({
                dots: true,
                slidesToShow: 4,
                slidesToScroll: 4,
                responsive: [{
                        breakpoint: 1024,
                        settings: {
                            slidesToShow: 3,
                            slidesToScroll: 3,
                            infinite: true,
                            dots: true
                        }
                    },
                    {
                        breakpoint: 600,
                        settings: {
                            slidesToShow: 2,
                            slidesToScroll: 2
                        }
                    },
                    {
                        breakpoint: 480,
                        settings: {
                            slidesToShow: 1,
                            slidesToScroll: 1
                        }
                    }
                ]
            });
        };


        /**
         * Saving the user's estimate for a task
         */
        ctrl.saveBid = function() {
            BidService.addOrUpdateTaskUserBid(ctrl.taskShow.task.id, ctrl.durationBid).then(function() {
                ctrl.taskShow.read = true;
                ctrl.taskShow.duration = angular.copy(ctrl.durationBid);
                ctrl.showTaskCarouselAndTinder();
            });
        };


        /**
         * Saving new tasks in bids
         */
        ctrl.saveNewTaskBid = function() {
            console.log();

            var listTaskId = [];
            for (var k in ctrl.listTaskWithoutUserSelected) {
                if (ctrl.listTaskWithoutUserSelected[k] === true) {
                    listTaskId.push(k);
                }
            }

            BidService.addBid(moment(ctrl.dateAdmin).valueOf(), listTaskId).then(function(data) {
                ctrl.listTaskBid = ctrl.listTaskBid.concat(data);
                ctrl.showTaskCarouselAndTinder(true);
                for (var k in ctrl.listTaskWithoutUserSelected) {
                    if (ctrl.listTaskWithoutUserSelected[k] === true) {
                        var index = ctrl.listTaskWithoutUser.findIndex(function(element) { return element.id == k });
                        if (index > -1) {
                            ctrl.listTaskWithoutUser.splice(index, 1)
                        }
                    }
                }
                ctrl.listTaskWithoutUserSelected = [];
            });
        };


        ctrl.init();
    })
})();