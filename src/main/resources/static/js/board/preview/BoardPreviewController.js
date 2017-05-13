(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('BoardPreviewController', function($state, $stateParams, BoardService) {
            var ctrl = this;

            ctrl.init = function() {
                ctrl.openPanelFilter = false;
            };

            ctrl.openPanelFilterAction = function(element) {
                $(element).slideToggle(500);
            }

            ctrl.init();
        })
})();