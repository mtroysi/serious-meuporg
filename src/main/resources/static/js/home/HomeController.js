/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('HomeController', function() {
        var ctrl = this;

        ctrl.init = function() {
            $('body').addClass('bg-white');
        };

        ctrl.init();
    })
})();