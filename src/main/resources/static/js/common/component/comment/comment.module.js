/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentCommentController(CommonCommentService) {
        var ctrl = this;
        ctrl.editable = false;

        ctrl.toggleEditable = function () {
            ctrl.editable = !ctrl.editable;
        };
    }


    angular.module('hello')
        .component('commentDiv', {
            controller: ComponentCommentController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/comment/comment.view.html',
            bindings: {
                comment: '='
            }
        });
})();