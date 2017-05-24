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

            ctrl.init = function () {
                ctrl.comment = {};
                CommentService.showComment($stateParams.id).then(function (data) {
                    ctrl.comment = data;
                });
            };

            ctrl.toggleEditable = function () {
                ctrl.editable = !ctrl.editable;
            };


            ctrl.updateComment = function () {
                CommentService.updateComment($stateParams.id, ctrl.comment).then(function (data) {
                    ctrl.comment = data;
                });
            };

            ctrl.deleteComment = function () {
                CommentService.deleteComment($stateParams.id).then(function (data) {
                    ctrl.comment = data;
                });
            };


            ctrl.init();
        })
})();