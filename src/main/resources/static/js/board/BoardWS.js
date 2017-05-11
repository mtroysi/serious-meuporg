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
            return $http.post(constant.BASE_URI + '/board/create', name);
        };

        return svc;
    })
})();