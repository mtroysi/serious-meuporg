(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('BidWS', function($http, constant) {
        var svc = this;

        /**
         * Retourne la liste des enchère d'un utilisateur pour un tableau
         * @param boardId id du tableau
         * @param userId id de l'utilisateur
         * @returns {HttpPromise}
         */
        svc.getListBidByBoardAndUser = function(boardId, userId) {
            return $http.get(constant.BASE_URI + '/bid/board/' + boardId + '/user/' + userId);
        };

        /**
         * Retourne la liste des tâches aux enchères qui sont finies
         * @param boardId id du tableau
         * @returns {HttpPromise}
         */
        svc.getListBidEndByBoard = function(boardId) {
            return $http.get(constant.BASE_URI + '/bid/end/board/' + boardId);
        };

        /**
         * Affecte un temps à une tâche aux enchères
         * @param taskId id de la tâche
         * @param duration durée
         * @returns {HttpPromise}
         */
        svc.addOrUpdateTaskUserBid = function(taskUserId, duration) {
            return $http.post(constant.BASE_URI + '/bid/task/' + taskUserId, duration);
        };

        /**
         * Ajoute une ou plusieurs tâches aux enchères
         * @param date date de fin de l'enchère
         * @param listTaskId liste des id des tâches à metre en enchère
         * @returns {HttpPromise}
         */
        svc.addBid = function(date, listTaskId) {
            return $http.post(constant.BASE_URI + '/bid/?dateend=' + date, listTaskId);
        };

        /**
         * Valide les encheres d'un tableau
         * @param boardId id du tableau
         * @param json liste de BidDTO
         * @returns {HttpPromise}
         */
        svc.validBid = function(boardId, json) {
            return $http.post(constant.BASE_URI + '/bid/valid/board/' + boardId, json);
        };

        return svc;
    })
})();