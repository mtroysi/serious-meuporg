/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('CommentController', function (CommentService, $stateParams) {
            var ctrl = this;

            ctrl.editable = false;

            /**
             * Initialisation
             */
            ctrl.init = function () {
                ctrl.comment = {};
                CommentService.showComment($stateParams.id).then(function (data) {
                    ctrl.comment = data;
                });
            };

            /**
             * Indique si le commentaire est en cours de modification ou non (affichage de l'input)
             */
            ctrl.toggleEditable = function () {
                ctrl.editable = !ctrl.editable;
            };


            /**
             * Modifie un commentaire
             */
            ctrl.updateComment = function () {
                CommentService.updateComment($stateParams.id, ctrl.comment).then(function (data) {
                    ctrl.comment = data;
                });
            };

            /**
             * Supprime un commentaire
             */
            ctrl.deleteComment = function () {
                CommentService.deleteComment($stateParams.id).then(function (data) {
                    ctrl.comment = data;
                });
            };


            ctrl.init();
        })
})();