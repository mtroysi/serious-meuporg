/**
 * Created by Sara on 18/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('UserWS', function($http, constant) {
            var svc = this;

            /**
             * Retourne un utilisateur
             * @param id l'id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.getUser = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id);
            };

            /**
             * Retourne tous les utilisateurs de l'application
             * @returns {HttpPromise}
             */
            svc.getAllUser = function() {
                return $http.get(constant.BASE_URI + '/user/list');
            };

            /**
             * Modifie un utilisateur
             * @param user utilisateur modifié
             * @returns {HttpPromise}
             */
            svc.editUser = function(user) {
                return $http.put(constant.BASE_URI + '/user', user);
            };

            /**
             * Retourne les statistiques d'un utilisateur
             * @param id l'id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.getStats = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id + '/stats');
            };

            /**
             * Retourne le classement de l'utilisateur dans les différentes catégories
             * @param id l'id de l'utilisateur
             * @returns {HttpPromise}
             */
            svc.getRankin = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id + '/rankin');
            };

            /**
             * Retourne les 10 meilleurs utilisateurs dans chaque catégorie
             * @returns {HttpPromise}
             */
            svc.getTopUsers = function() {
                return $http.get(constant.BASE_URI + '/user/top10');
            };

            
            return svc;
        })
})();