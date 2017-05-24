/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentCommentController(AuthenticationService) {
        var ctrl = this;
        ctrl.editable = false;

        ctrl.toggleEditable = function () {
            return ctrl.editable = !ctrl.editable;
        };

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