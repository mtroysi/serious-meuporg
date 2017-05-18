/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonCommentService', function (CommonCommentWS) {
            var svc = {};

            svc.deleteComment = function (id) {
                return CommonCommentWS.deleteComment(id).then(function (response) {
                    return response.data;
                });
            };

            return svc;
        });
})();