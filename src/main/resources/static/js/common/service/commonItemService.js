(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonItemService', function(CommonNotificationBoxService, $interval) {
        var svc = {};
        svc.items = [];

        svc.run = function() {
            var removeWallpaper = true;
            svc.spellHappy = false;

            svc.items.forEach(function(item) {
                // ACTIVATION DES ITEMS
                if (item.active === true) {
                    if (item.type === 'CURSE') {
                        svc.addCurse(item);
                    }
                    if (item.type === 'SPELL') {
                        svc.addSpell(item);
                    }
                    if (item.type === "WALLPAPER") {
                        svc.addWallpaper(item);
                        removeWallpaper = false;
                    }
                }

                // DESACTIVE LES ITEMS (DESACTIVE)
                if (removeWallpaper) {
                    svc.removeWallpaper();
                }
            });
        };

        /* AJOUT d'un fond d'écran */
        svc.addWallpaper = function(item) {
            $('.static-content-wrapper').css({
                'background-size': 'cover',
                'background-image': "url('" + item.image + "')"
            });
        };

        /* AJOUT d'un fond d'écran */
        svc.removeWallpaper = function() {
            $('.static-content-wrapper').css({
                'background-image': ''
            });
        };

        svc.addSpell = function(item) {
            if (item.keyItem === 'SPELL_HAPPY') {

                //On supprime la derniere instance
                if (svc.interval) {
                    $interval.cancel(svc.interval);
                }
                svc.interval = $interval(svc.spellHappyAction, 20000);

                svc.spellHappy = true;

            }
        };

        svc.addCurse = function(item) {
            if (item.keyItem === 'CURSE_SNOW') {
                $.fn.snow();
            }

            if (item.keyItem === 'CURSE_BREBIS') {
                if ($('body audio.brebis').length == 0) {
                    $('body').append($('<audio class="brebis" autoplay><source src="assets/audio/mouton.mp3" type="audio/mp3"></source></audio>'));
                }
            }
        };

        svc.spellHappyAction = function() {
            var messageGentil = ["Tu es le fruit de la passion dans ma salade d’agrumes.", "On devrait donner ton nom à une fleur/une glace.", " Mon plus beau cadeau de Noël, c’est toi…", "Mon appareil photo n’est pas à la hauteur de ta beauté.", "Le plus beau langage du monde est celui que tes yeux utilisent", "Tu es beau/ belle à piquer les yeux des filles/garçons. (Aïe !)", "Ton sourire pourrait éclairer le fond de l’océan.", "Je pourrais te contempler éternellement.", "J’aime ta façon de marcher.", " Tu as une beauté préraphaélite.", "Rodin n’aurait pas fait mieux que Toi.", "Tu es irremplaçable.", "Tu apportes de la lumière à ma vie.", "Je suis totalement désarçonné par ton intelligence.", "Le son de ta voix me caresse jusqu’en bas du dos.", ];
            var item = messageGentil[Math.floor(Math.random() * messageGentil.length)];
            if (svc.spellHappy) {
                CommonNotificationBoxService.messageAdore(item);
            }
        }

        svc.setItems = function(data) {
            svc.items = data;
            svc.run();
        }

        return svc;
    })
})();