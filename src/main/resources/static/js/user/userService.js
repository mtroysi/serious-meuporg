/**
 * Created by sara on 18/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('UserService', function(UserWS) {
            var svc = {};

            /**
             * Retourne un utilisateur
             * @param id l'id de l'utilisateur
             */
            svc.getUser = function(id) {
                return UserWS.getUser(id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retourne tous les utilisateurs de l'application
             */
            svc.getAllUser = function() {
                return UserWS.getAllUser().then(function(response) {
                    return response.data;
                });
            };

            /**
             * Modifie un utilisateur
             * @param user utilisateur modifié
             */
            svc.editUser = function(user) {
                return UserWS.editUser(user).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retourne les statistiques d'un utilisateur
             * @param id l'id de l'utilisateur
             */
            svc.getStats = function(id) {
                return UserWS.getStats(id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retourne le classement de l'utilisateur dans les différentes catégories
             * @param id l'id de l'utilisateur
             */
            svc.getRankin = function(id) {
                return UserWS.getRankin(id).then(function(response) {
                    return response.data;
                });
            };

            /**
             * Retourne les 10 meilleurs utilisateurs dans chaque catégorie
             */
            svc.getTopUsers = function(){
                return UserWS.getTopUsers().then(function(response) {
                    return response.data;
                }); 
            };

            return svc;
        });
})();