(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonMenuService', function() {
        var svc = {};

        svc.listBoard = [];

        /**
         * Retourne la liste des tableaux de l'utilisateur
         * @returns {Array|*}
         */
        svc.getListBoard = function() {
            return svc.listBoard;
        };

        /**
         * Ajoute un tableau à la liste
         * @param element tableau à ajouter
         */
        svc.addListBoard = function(element) {
            if (!svc.listBoard) {
                svc.listBoard = [];
            }
            svc.listBoard.push(element);
        };

        /**
         * Modifie un tableau de la liste
         * @param newElement tableau à modifier
         */
        svc.updateElementListBoard = function(newElement) {
            var index = svc.listBoard.findIndex(function(element) { return element.id == newElement.id });
            if (index != -1) {
                svc.listBoard[index] = newElement;
            }
        };

        /**
         * Retire un tableau de la liste
         * @param board_id id du tableau à retirer
         */
        svc.removeListBoard = function(board_id) {
            var index = _.findIndex(svc.listBoard, { id: board_id });
            svc.listBoard.splice(index, 1);
        };

        /**
         * Initialise la liste des tableaux avec une liste de valeurs
         * @param elements liste de tableaux
         */
        svc.initListBoard = function(elements) {
            svc.listBoard = elements;
        };

        return svc;
    });
})();