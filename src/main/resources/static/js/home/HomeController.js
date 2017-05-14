/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('HomeController', function($location, $http, HomeService) {
        var ctrl = this;

        ctrl.init = function() {};

        ctrl.init();
    })
})();