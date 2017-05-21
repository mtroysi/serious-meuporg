(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('EnchereService', function(EnchereWS) {
        var svc = {};

        svc.getListEnchereByBoardAndUser = function(boardId, userId) {
            return EnchereWS.getListEnchereByBoardAndUser(boardId, userId).then(function(response) {
                return response.data;
            });
        };

        return svc;
    })
})();