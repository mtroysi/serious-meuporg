(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('EnchereWS', function($http, constant) {
        var svc = this;


        svc.getListEnchereByBoardAndUser = function(boardId, userId) {
            return $http.get(constant.BASE_URI + '/enchere/board/' + boardId + '/user/' + userId);
        };

        return svc;
    })
})();