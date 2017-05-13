/**
 * Created by Morgane TROYSI on 11/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('BoardService', function(BoardWS) {
            var svc = {};

            svc.createBoard = function(name) {
                return BoardWS.createBoard(name).then(function(response) {
                    return response.data;
                });
            };

            svc.listBoardByUser = function(user_id) {
                return BoardWS.listBoardByUser(user_id).then(function(response) {
                    return response.data;
                });
            };

            return svc;
        })
})();