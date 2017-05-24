(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('BidValidationController', function($stateParams, $location, $http, AuthenticationService, BidService) {
        var ctrl = this;

        ctrl.init = function() {
            BidService.getListBidEndByBoard($stateParams.idBoard).then(function() {

            });
        };

        ctrl.init();
    })
})();