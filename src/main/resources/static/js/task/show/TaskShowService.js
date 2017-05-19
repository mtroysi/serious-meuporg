/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('TaskShowService', function (TaskShowWS) {
            var svc = {};

            svc.showTask = function (id) {
                return TaskShowWS.showTask(id).then(function (response) {
                    return response.data;
                });
            };

            svc.addComment = function (id, comment, creator) {
                comment.creator = creator;
                return TaskShowWS.addComment(id, comment).then(function (response) {
                    return response.data;
                });
            };

            svc.deleteComment = function (id) {
                return TaskShowWS.deleteComment(id).then(function (response) {
                    return response.data;
                });
            };

            svc.updateComment = function (id, comment) {
                return TaskShowWS.updateComment(id, comment).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        })
})();