(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonItemService', function() {
        var svc = {};
        svc.items = [];

        svc.run = function() {
            var removeWallpaper = true;

            svc.items.forEach(function(item) {
                // ACTIVATION DES ITEMS
                if (item.active === true) {
                    if (item.type === 'CURSE') {

                    }
                    if (item.type === 'SPELL') {

                    }
                    if (item.type === "WALLPAPER") {
                        svc.addWallpaper(item);
                        removeWallpaper = false;
                    }
                }
                // DESACTIVE LES ITEMS (DESACTIVE)
                if (removeWallpaper) {
                    $('.static-content-wrapper').css({
                        'background-image': ''
                    });
                }
            });
        }

        /* AJOUT d'un fond d'écran */
        svc.addWallpaper = function(item) {
            $('.static-content-wrapper').css({
                'background-size': 'cover',
                'background-image': "url('" + item.image + "')"
            });
        }

        /* AJOUT d'un fond d'écran */
        svc.removeWallpaper = function(item) {

        }

        svc.setItems = function(data) {
            svc.items = data;
            svc.run();
        }

        return svc;
    })
})();