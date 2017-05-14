(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonMenuService', function() {
        var svc = {};

        svc.listBoard = [];

        svc.getListBoard = function() {
            return svc.listBoard;
        };

        svc.addListBoard = function(element) {
            svc.listBoard.push(element);
        };

        svc.initListBoard = function(elements) {
            svc.listBoard = elements;
        };

        return svc;
    });
})();