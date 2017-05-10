/**
 * Created by Morgane TROYSI on 22/04/2017.
 */
(function () {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('HomeService', function (HomeWS) {
        var svc = {};

        svc.getResource = function () {
           return HomeWS.getResource().then(function (response) {
                return response.data;
            })
        };

        return svc;
    })
})();


