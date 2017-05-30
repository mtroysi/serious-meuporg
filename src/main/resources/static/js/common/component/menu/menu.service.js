(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonMenuService', function() {
        var svc = {};

        svc.listBoard = [];

        svc.getListBoard = function() {
            return svc.listBoard;
        };

        svc.addListBoard = function(element) {
            if (!svc.listBoard) {
                svc.listBoard = [];
            }
            svc.listBoard.push(element);
        };

        svc.updateElementListBoard = function(newElement) {
            var index = svc.listBoard.findIndex(function(element) { return element.id == newElement.id });
            if (index != -1) {
                angular.extend(svc.listBoard[index], svc.listBoard[index], newElement);
            }
        };

        svc.removeListBoard = function(board_id) {
            var index = _.findIndex(svc.listBoard, { id: board_id });
            svc.listBoard.splice(index, 1);
        };

        svc.initListBoard = function(elements) {
            svc.listBoard = elements;
        };

        return svc;
    });
})();