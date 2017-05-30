(function() {
    'use strict';

    function ComponentNotificationController(NotificationService, CommonNotificationService, AuthenticationService, $scope) {
        var ctrl = this;

        ctrl.init = function() {
            ctrl.listNotification = [];
            ctrl.CommonNotificationService = CommonNotificationService;

            // Call WS for get the list of notification of the user
            NotificationService.getNotificationByUser(AuthenticationService.getUserId()).then(function(data) {
                CommonNotificationService.initListNotification(angular.copy(data));
            });

            /**
             * Watch the list of menu (list of notification)
             * Variable to watch : listNotification
             */
            $scope.$watch('this.vm.CommonNotificationService.listNotification', function(newValues) {
                ctrl.listNotification = newValues;
            });
        };

        /**
         * Marque toutes les notifications comme lues
         */
        ctrl.readAllNotif = function() {
            // The user has just seen all notifications
            NotificationService.readAllNotification(AuthenticationService.getUserId());
        };

        ctrl.init();
    }


    angular.module('hello').component('mgNotification', {
        transclude: true,
        controller: ComponentNotificationController,
        controllerAs: 'vm',
        templateUrl: 'js/common/component/notification/notification.view.html'
    });
})();