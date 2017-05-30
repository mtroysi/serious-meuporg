/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommentWS', function ($http, constant) {
            var svc = this;

            /**
             * Affiche un commentaire
             * @param id l'id du commentaire
             * @returns {HttpPromise}
             */
            svc.showComment = function (id) {
                return $http.get(constant.BASE_URI+'/comment/'+id);
            };

            /**
             * Modifie un commentaire
             * @param id l'id du commentaire
             * @param comment données modifiées du commentaire
             * @returns {HttpPromise}
             */
            svc.updateComment = function (id, comment) {
                return $http.put(constant.BASE_URI + '/comment/'+id, comment);
            };

            /**
             * Supprime un commentaire
             * @param id l'id du commentaire
             * @returns {HttpPromise}
             */
            svc.deleteComment = function (id) {
                return $http.delete(constant.BASE_URI + '/comment/'+id);
            };

            return svc;
        })
})();