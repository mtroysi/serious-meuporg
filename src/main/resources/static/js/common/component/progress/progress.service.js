(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonProgressService', function() {
        var svc = {};

        svc.money = [];

        svc.getMoney = function() {
            return svc.money;
        };

        svc.setMoney = function(money) {
            svc.money = money;
        };

        return svc;
    });
})();