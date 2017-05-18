/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('CommentWS', function ($http, constant) {
            var svc = this;

            svc.showComment = function (id) {
                return $http.get(constant.BASE_URI+'/comment/'+id);
            };

            svc.updateComment = function (id, task) {
                return $http.put(constant.BASE_URI + '/comment/'+id, task);
            };

            svc.deleteComment = function (id) {
                return $http.delete(constant.BASE_URI + '/comment/'+id);
            };

            return svc;
        })
})();