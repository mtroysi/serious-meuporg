/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardWS', function ($http, constant) {
        var svc = this;

        svc.createBoard = function (name) {
            return $http.post(constant.BASE_URI + '/board', name);
        };

        svc.updateBoard = function (id, jsonToSend) {
            return $http.put(constant.BASE_URI + '/board/' + id, jsonToSend);
        };

        svc.deleteBoard = function (id) {
            return $http.put(constant.BASE_URI + '/board/' + id);
        };

        return svc;
    })
})();