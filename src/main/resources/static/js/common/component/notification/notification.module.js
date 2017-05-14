(function() {
    'use strict';

    function ComponentNotificationController() {
        var ctrl = this;

        ctrl.init = function() {

        };

        ctrl.init();
    }


    angular.module('hello').component('mgNotification', {
        transclude: true,
        controller: ComponentNotificationController,
        templateUrl: 'js/common/component/notification/notification.view.html'
    });
})();