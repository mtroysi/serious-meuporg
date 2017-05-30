/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskUpdateWS', function ($http, constant) {
            var svc = this;

            svc.createTask = function (task) {
                return $http.post(constant.BASE_URI + '/taskUser/', task);
            };

            /**
             * Modifie une tâche
             * @param id de la tâche à modifier
             * @param task données modifiées
             * @returns {HttpPromise}
             */
            svc.updateTask = function (id, task) {
                return $http.put(constant.BASE_URI + '/taskUser/' + id, task);
            };

            /**
             * Supprime une tâche
             * @param id de la tâche à supprimer
             * @returns {HttpPromise}
             */
            svc.deleteTask = function (id) {
                return $http.delete(constant.BASE_URI + '/taskUser/' + id);
            };

            /**
             * Ajoute ou enlève un tag d'une fonction
             * @param idTask
             * @param idTag
             * @returns {HttpPromise}
             */
            svc.toggleTag = function (idTask, idTag) {
                return $http.put(constant.BASE_URI + '/task/' + idTask + '/tag/' + idTag);
            };

            return svc;
        })
})();