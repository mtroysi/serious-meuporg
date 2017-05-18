/**
 * Created by Morgane TROYSI on 16/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommonCommentWS', function($http, constant) {
            var svc = this;

            svc.deleteComment = function(id) {
                return $http.delete(constant.BASE_URI + '/comment/'+id);
            };

            return svc;
        })
})();