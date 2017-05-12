(function() {
    'use strict';

    function ComponentMenuController() {
        var ctrl = this;

        ctrl.init = function() {};

        ctrl.init();
    }


    angular.module('hello', ['ui.router']).component('mgMenu', {
        controller: ComponentMenuController,
        templateUrl: 'js/common/component/menu/menu.view.html'
    });
})();