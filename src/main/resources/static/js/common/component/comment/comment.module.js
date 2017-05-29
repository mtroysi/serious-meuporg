/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentCommentController(AuthenticationService) {
        var ctrl = this;
        ctrl.editable = false;

        /**
         * Indique si le commentaire est en cours d'édition
         * @returns {boolean}
         */
        ctrl.toggleEditable = function () {
            return ctrl.editable = !ctrl.editable;
        };

        /**
         * Retourne vrai si l'auer du commentaire est l'utilisateur connecté
         * @returns {boolean}
         */
        ctrl.isOwner = function () {
            return ctrl.comment.creator.id === Number(AuthenticationService.getUserId());
        }
    }


    angular.module('hello')
        .component('commentDiv', {
            controller: ComponentCommentController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/comment/comment.view.html',
            bindings: {
                comment: '=',
                deleteComment: "&",
                updateComment: '&'
            }
        });
})();