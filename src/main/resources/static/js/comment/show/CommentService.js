/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommentService', function (CommentWS) {
            var svc = {};

            svc.showComment  = function (id) {
                return CommentWS.showComment(id).then(function (response) {
                    return response.data;
                });
            };

            svc.updateComment = function (id, comment) {
                return CommentWS.updateComment(id, comment).then(function (response) {
                    return response.data;
                });
            };

            svc.deleteComment = function (id) {
                return CommentWS.deleteComment(id).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        })
})();