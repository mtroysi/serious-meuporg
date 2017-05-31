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
            /* Gestion du bouton pour remonter tout en haut de la page */
            $(window).scroll(function() {
                if ($(this).scrollTop() >= 50) {
                    $('#return-to-top').fadeIn(200);
                } else {
                    $('#return-to-top').fadeOut(200);
                }
            });
            $('#return-to-top').click(function() {
                $('body,html').animate({
                    scrollTop: 0
                }, 500);
            });
        };

        ctrl.init();
    })
})();