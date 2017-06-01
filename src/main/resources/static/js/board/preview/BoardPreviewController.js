(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardPreviewController', function($scope, TaskShowService, $rootScope, $state, $stateParams, BoardService, $timeout, CommonMenuService, TaskService, AuthenticationService, CommonDialogService) {
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
                ctrl.tmpColonneKanbanByBoard = [];
                ctrl.newColonne = {};
                ctrl.editColonne = {};
                ctrl.filter = { type: 'TOUT' };
                ctrl.isAdmin = false;
                ctrl.tableGlobal = false;
                ctrl.task = {};
                ctrl.tags = {};


                $rootScope.$on('$viewContentLoaded', function() {
                    ctrl.activeDragAndDrop();
                });

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
                    // ctrl.getTaskBoard($stateParams.idBoard, AuthenticationService.getUserId());
                }

                $scope.$watch('this.ctrl.filter.type', function() {
                    ctrl.filterTask();
                });
            };

            /**
             * WS Loard info board
             */
            ctrl.getBoard = function(id) {
                if (($stateParams.idtask) && (!$stateParams.idBoard)) {
                    BoardService.getBoardFromTask($stateParams.idtask).then(function(response) {
                        ctrl.board = response;
                        ctrl.isAdmin = (Number(AuthenticationService.getUserId()) === ctrl.board.creator.id);
                        ctrl.getTaskBoard(ctrl.board.id, AuthenticationService.getUserId());
                    });
                } else {
                    BoardService.getBoard(id).then(function(data) {
                        ctrl.board = data;
                        ctrl.isAdmin = (Number(AuthenticationService.getUserId()) === ctrl.board.creator.id);
                        ctrl.getTaskBoard(ctrl.board.id, AuthenticationService.getUserId());
                    });
                }
            };

            /**
             * WS Loard list of task by board and/or user
             */
            ctrl.getTaskBoard = function(board_id, user_id) {
                // Task of the team 
                if (ctrl.typeDisplayTeam === true) {
                    TaskShowService.listTaskByBoard(board_id).then(function(fetchData) {
                        ctrl.listTaskDefault = angular.copy(fetchData);
                        ctrl.listTask = angular.copy(fetchData);
                    });
                } else {
                    TaskShowService.listTaskByBoardAndUser(board_id, user_id).then(function(fetchData) {
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
                TaskShowService.listTaskByUser(user_id).then(function(fetchData) {
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
             * View TEAM Or INDIVIDUELLE
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

            /**
             * Open a modal to edit a task
             */
            ctrl.editTaskAction = function(task) {
                $('#editTask').modal('show');
                var testGetBoard = false;
                var args = {};
                args.task = task;

                if (ctrl.board !== null) {
                    args.colonneKanban = ctrl.board.colonneKanbans;
                } else {
                    // Vue d'ensemble
                    if ("board" + task.task.boardId in ctrl.tmpColonneKanbanByBoard) {
                        args.colonneKanban = ctrl.tmpColonneKanbanByBoard["board" + task.task.boardId];
                    } else {
                        BoardService.getBoard(task.task.boardId).then(function(data) {
                            ctrl.tmpColonneKanbanByBoard["board" + task.task.boardId] = data.colonneKanban;
                            args.colonneKanban = data.colonneKanbans;
                            $scope.$broadcast("showTask", args);
                        });
                    }
                }

                if (testGetBoard === false) {
                    $scope.$broadcast("showTask", args);
                }
            };

            /**
             * Open a modal to create a new task
             */
            ctrl.createTaskAction = function() {
                $('#editTask').modal('show');
                var args = {};
                args.task = {};
                args.task.task = {};
                args.task.task.boardId = ctrl.board.id;
                args.task.task.creator = Number(AuthenticationService.getUserId());
                args.colonneKanban = ctrl.board.colonneKanbans;
                args.moneyValue = ctrl.board.moneyDoneTask;
                args.experienceValue = ctrl.board.expDoneTask;

                $scope.$broadcast("createTask", args);
            };


            $scope.$on("deleteTask", function(event, args) {
                var t = args.task;
                ctrl.listTask.splice(ctrl.listTask.indexOf(t), 1);
                ctrl.listTaskDefault.splice(ctrl.listTaskDefault.indexOf(t), 1);
                ctrl.filterTask();
            });

            $scope.$on("createdTask", function(event, args) {
                if (args.task.user.id === Number(AuthenticationService.getUserId())) {
                    ctrl.listTask.push(args.task);
                    ctrl.listTaskDefault.push(args.task);
                }
                ctrl.filterTask();
            });

            /** On va supprimer les taches qui se sont plus affectées à l'utilisateur courant */
            $scope.$on("updateTask", function() {
                // Si on se trouve en mode individuelle
                if ($state.current.name === "app.board-preview") {
                    console.log(ctrl.listTaskDefault);
                    ctrl.listTaskDefault = ctrl.listTaskDefault.filter(function(e) {
                        var sum = 0;
                        e.user.forEach(function(u, initialValue) {
                            sum += u.id === Number(AuthenticationService.getUserId()) ? 1 : 0;
                        });
                        return sum > 0;
                    });
                    ctrl.listTask = angular.copy(ctrl.listTaskDefault);
                    ctrl.filterTask();
                }
            });

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
            };


            /**
             * Save new column Kanban
             */
            ctrl.saveNewColonne = function() {
                var colonneKanban = { id: null, title: ctrl.newColonne.title, color: ctrl.newColonne.color };
                BoardService.createColonneKanban(colonneKanban, ctrl.board.id).then(function(response) {
                    ctrl.board.colonneKanbans.push(response);
                    $timeout(function() {
                        ctrl.sizeKanban();
                        ctrl.activeDragAndDrop();
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
                    return element.id === ctrl.editColonne.id
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
                    return element.id === idColonne
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


            /**
             * Active drag And Drop
             */
            ctrl.activeDragAndDrop = function() {
                setTimeout(function() {
                    $("#sortable1, #sortable2, #sortable3, #sortable4").sortable({
                        connectWith: ".boxElementTile",
                        appendTo: 'body',
                        containment: 'window',
                        dropOnEmpty: true,
                        scroll: false,
                        helper: 'clone',
                        receive: function(event, ui) {
                            var periodicy = $(event.target).data('task');
                            var idTask = $(ui.item[0]).children().data('idtask');
                            TaskService.updatePriorityTask(idTask, periodicy).then(function(fetchData) {
                                ctrl.updateLocalListTask(idTask, fetchData);
                            });
                        }
                    }).disableSelection();

                    $(".listTache").sortable({
                        connectWith: ".listTache",
                        appendTo: 'body',
                        containment: 'window',
                        dropOnEmpty: true,
                        scroll: false,
                        helper: 'clone',
                        receive: function(event, ui) {
                            var newColonne = $(event.target).data('column');
                            var idTask = $(ui.item[0]).children().data('idtask');
                            TaskService.updateColumnTask(idTask, newColonne === "NO_ASSIGNE" ? null : newColonne).then(function(fetchData) {
                                ctrl.updateLocalListTask(idTask, fetchData);
                            });
                        }
                    }).disableSelection();
                }, 200);
            };


            /**
             * Update la liste des tasks
             */
            ctrl.updateLocalListTask = function(idTaskUser, newElement) {
                // Updating Task Lists
                ctrl.listTaskDefault.forEach(function(element) {
                    if (element.id === idTaskUser) {
                        angular.extend(element, element, newElement);
                    }
                });
                ctrl.listTask = angular.copy(ctrl.listTaskDefault);
                ctrl.filterTask();
            }

            ctrl.init();
        });
})();