/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentCommentController(AuthenticationService, $scope, constant) {
        var ctrl = this;
        ctrl.editable = false;

        $scope.$watch('this.ctrl.comment.creator.avatar', function() {
            if (!ctrl.comment.creator.avatar) {
                ctrl.comment.creator.avatar = constant.URL_IMAGE_DEFAULT;
            }
        });

        /**
         * Indique si le commentaire est en cours d'édition
         * @returns {boolean}
         */
        ctrl.toggleEditable = function () {
            return ctrl.editable = !ctrl.editable;
        };

        /**
         * Retourne vrai si l'auteur du commentaire est l'utilisateur connecté
         * @returns {boolean}
         */
        ctrl.isOwner = function () {
            return ctrl.comment.creator.id === Number(AuthenticationService.getUserId());
        };
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