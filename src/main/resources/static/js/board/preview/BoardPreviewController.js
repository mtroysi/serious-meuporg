(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardPreviewController', function($scope, $state, $stateParams, BoardService, $timeout, CommonMenuService, TaskService, AuthenticationService, CommonDialogService) {
            var ctrl = this;
            ctrl.isAdmin = false;

            /**
             * Constructor
             */
            ctrl.init = function() {
                ctrl.openPanelFilter = false;
                ctrl.openPanelNewColonne = false;
                ctrl.typeDisplayTeam = false;
                ctrl.listTask = [];
                ctrl.listTaskDefault = [];
                ctrl.newColonne = {};
                ctrl.editColonne = {};
                ctrl.filter = { type: 'TOUT' };
                ctrl.isAdmin = false;
                ctrl.tableGlobal = false;
                ctrl.task = {};
                ctrl.tags = {};

                $scope.tagPopover = {
                    content: 'Hello, World!',
                    templateUrl: 'js/tag/tag-list.html',
                    title: 'Ajouter un tag'
                };

                // Show table in global mode
                if ($state.current.name === "app.board-preview-common") {
                    ctrl.tableGlobal = true;
                    ctrl.getTaskUser(AuthenticationService.getUserId());
                } else {
                    ctrl.getBoard($stateParams.idBoard);
                    ctrl.getTaskBoard($stateParams.idBoard, AuthenticationService.getUserId());
                }

                $scope.$watch('this.ctrl.filter.type', function() {
                    ctrl.filterTask();
                });
            };

            /**
             * WS Loard info board
             */
            ctrl.getBoard = function(id) {
                BoardService.getBoard(id).then(function(data) {
                    ctrl.board = data;
                    ctrl.isAdmin = (Number(AuthenticationService.getUserId()) === ctrl.board.creator.id);
                });
            };

            /**
             * WS Loard list of task by board and/or user
             */
            ctrl.getTaskBoard = function(board_id, user_id) {
                // Task of the team 
                if (ctrl.typeDisplayTeam === true) {
                    TaskService.listTaskByBoard(board_id).then(function(fetchData) {
                        ctrl.listTaskDefault = angular.copy(fetchData);
                        ctrl.listTask = angular.copy(fetchData);
                    });
                } else {
                    TaskService.listTaskByBoardAndUser(board_id, user_id).then(function(fetchData) {
                        ctrl.listTaskDefault = angular.copy(fetchData);
                        ctrl.listTask = angular.copy(fetchData);

                        // Open Modal
                        if ($stateParams.idtask !== undefined) {
                            var maTask = ctrl.listTask.find(function(task) {
                                return task.task.id === Number($stateParams.idtask);
                            });

                            if (maTask !== undefined) {
                                ctrl.editTaskAction(maTask);
                            }
                        }
                    });
                }
            };

            /**
             * WS Loard list of task by  user
             */
            ctrl.getTaskUser = function(user_id) {
                TaskService.listTaskByUser(user_id).then(function(fetchData) {
                    ctrl.listTaskDefault = angular.copy(ctrl.addColorTask(fetchData));
                    ctrl.listTask = angular.copy(ctrl.listTaskDefault);
                });

            };

            /**
             * Adding the color of the associated board
             */
            ctrl.addColorTask = function(data) {
                var listBoard = CommonMenuService.getListBoard();
                var listColorBoard = {};

                listBoard.forEach(function(board) {
                    listColorBoard[board.id] = board.color;
                });

                data.forEach(function(element) {
                    element.colorBoard = listColorBoard[element.task.boardId];
                });
                return data;
            };

            /**
             * Filter Data Task
             */
            ctrl.filterTask = function() {
                ctrl.listTask = ctrl.listTaskDefault.filter(function(e) {
                    return e.task.status === ctrl.filter.type || ctrl.filter.type === 'TOUT';
                });
            };

            /**
             * VIew TEAM Or INDIVIDUELLE
             */
            ctrl.changeView = function() {
                ctrl.typeDisplayTeam = !ctrl.typeDisplayTeam;
                ctrl.listTask = [];
                ctrl.listTaskDefault = [];
                ctrl.getTaskBoard($stateParams.idBoard, AuthenticationService.getUserId());
            };

            /**
             * Open panel FILTER
             */
            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            };

            /**
             * Open panel Task (Kanban)
             */
            ctrl.zoomTask = function(type) {
                $($('.boxMatrice .panelMatrice').get().reverse()).each(function(index) {
                    $(this).fadeToggle(150);
                });
                $timeout(function() {
                    $('.bigPanelMatrice').fadeToggle();
                }, 200);
                ctrl.sizeKanban();
            };

            ctrl.editTaskAction = function(task) {
                $('#editTask').modal('show');
                ctrl.task = task;
                $scope.$broadcast("showTask", task);
            };


            /**
             * Box size management
             */
            ctrl.sizeKanban = function() {
                var width = 5;
                $('.contentKanban .columnKanban').each(function() {
                    width += $(this).width() + 52;
                });
                $('.contentKanban').width(width);
            };

            /**
             * Validation of deletion
             */
            ctrl.deleteColonneModal = function(idColonne) {
                CommonDialogService.confirmation('Etes-vous sur de vouloir supprimer cette élément ?', function() {
                    ctrl.deleteColonne(idColonne);
                }, null, 'modalDeleteColonne', "Suppression colonne Kanban", "Valider", "Annuler");
            };

            /**
             * Modal edition
             */
            ctrl.editColonneAction = function(colonne) {
                $('#editColonneKanban').modal("show");
                ctrl.editColonne = angular.copy(colonne);
            };

            /**
             * Edit color Colonne
             */
            ctrl.setColor = function(colonne) {
                ctrl.editColonne = colonne;
                ctrl.saveEditColonne();
            }


            /**
             * Save new column Kanban
             */
            ctrl.saveNewColonne = function() {
                var colonneKanban = { id: null, title: ctrl.newColonne.title, color: ctrl.newColonne.color };
                BoardService.createColonneKanban(colonneKanban, ctrl.board.id).then(function(response) {
                    ctrl.board.colonneKanbans.push(response);
                    $timeout(function() {
                        ctrl.sizeKanban();
                        //Reset form
                        ctrl.newColonne = {};
                        ctrl.openPanelNewColonne = false;
                    }, 0);
                });
            };

            /**
             * Save edit column Kanban
             */
            ctrl.saveEditColonne = function() {
                var index = ctrl.board.colonneKanbans.findIndex(function(element) {
                    return element.id == ctrl.editColonne.id
                });
                if (index !== -1) {
                    BoardService.editColonneKanban(ctrl.editColonne).then(function(response) {
                        ctrl.board.colonneKanbans[index] = response;
                    });
                }
            };

            /**
             * Delete column Kanban
             */
            ctrl.deleteColonne = function(idColonne) {
                var index = ctrl.board.colonneKanbans.findIndex(function(element) {
                    return element.id == idColonne
                });
                if (index !== -1) {
                    BoardService.deleteColonneKanban(idColonne).then(function(response) {
                        ctrl.board.colonneKanbans.splice(index, 1);

                        // Updating Task Lists
                        ctrl.listTaskDefault.forEach(function(element) {
                            if (element.task.colonneKanban && element.task.colonneKanban.id === idColonne) {
                                element.task.colonneKanban = null;
                            }
                        });
                        ctrl.listTask = angular.copy(ctrl.listTaskDefault);
                        ctrl.filterTask();
                    });
                }
            };

            /**
             * Delete Board
             */
            ctrl.deleteBoard = function() {
                BoardService.deleteBoard(ctrl.board.id).then(function() {
                    CommonMenuService.removeListBoard(ctrl.board.id);
                    $state.go('app.dashboard');
                });
            };

            ctrl.init();
        });
})();