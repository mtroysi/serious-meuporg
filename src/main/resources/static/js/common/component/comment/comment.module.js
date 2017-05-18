/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentCommentController() {
        var ctrl = this;
        ctrl.editable = false;

        ctrl.toggleEditable = function () {
            return ctrl.editable = !ctrl.editable;
        };
    }


    angular.module('hello')
        .component('commentDiv', {
            controller: ComponentCommentController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/comment/comment.view.html',
            bindings: {
                comment: '=',
                deleteComment:"&"
            }
        });
})();