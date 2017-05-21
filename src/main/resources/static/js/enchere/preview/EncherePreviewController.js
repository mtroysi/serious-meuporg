(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('EncherePreviewController', function($location, $http, BoardService, $stateParams, AuthenticationService, TaskService, EnchereService) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.initCarousel();
            ctrl.getBoard($stateParams.idBoard);
            ctrl.getListEnchereByBoardAndUser($stateParams.idBoard, AuthenticationService.getUserId());
            ctrl.date = null;
            // Admin
            ctrl.listTaskWithoutUserSelected = [];
            ctrl.listTaskWithoutUser = [];
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
        ctrl.getListEnchereByBoardAndUser = function(boardId, userId) {
            EnchereService.getListEnchereByBoardAndUser(boardId, userId).then(function(data) {
                ctrl.listTaskBid = data;
                ctrl.showTaskCarouselAndTinder(true);
            });
        }


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
        }

        /**
         * Change task
         */
        ctrl.changeTask = function(task) {
            ctrl.taskShow = task;
        }

        /**
         * Accepcted task
         */
        ctrl.acceptedTask = function() {
            ctrl.taskShow.read = true;
            ctrl.showTaskCarouselAndTinder();
        }

        /**
         * refused task
         */
        ctrl.refusedTask = function() {
            ctrl.taskShow.read = true;
            ctrl.showTaskCarouselAndTinder();
        }



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


        ctrl.init();
    })
})();