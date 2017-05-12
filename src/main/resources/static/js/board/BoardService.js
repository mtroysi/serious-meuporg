/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardService', function (BoardWS) {
        var svc = {};

        svc.createBoard = function (name) {
            return BoardWS.createBoard(name).then(function (response) {
                return response.data;
            })
        };

        svc.updateBoard = function (id, jsonToSend) {
            return BoardWS.updateBoard(id, jsonToSend).then(function (response) {
                return response.data;
            })
        };

        svc.deleteBoard = function (id) {
            return BoardWS.deleteBoard(id);
        };

        return svc;
    })
})();