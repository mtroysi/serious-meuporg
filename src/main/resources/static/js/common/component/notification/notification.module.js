(function() {
    'use strict';

    function ComponentNotificationController(NotificationService, CommonNotificationService, AuthenticationService, $scope) {
        var ctrl = this;

        ctrl.init = function() {
            // Call WS for get the list of notification of the user
            NotificationService.getNotificationByUser(AuthenticationService.getUserId()).then(function(data) {
                CommonNotificationService.initListNotification(angular.copy(data));
            });

            /**
             * Watch the list of menu (list of board)
             * Variable to watch : listBoard
             */
            $scope.$watch('this.vm.CommonNotificationService.listBoard', function(newValues) {
                ctrl.listBoard = newValues;
            });
        };

        ctrl.init();
    }


    angular.module('hello').component('mgNotification', {
        transclude: true,
        controller: ComponentNotificationController,
        templateUrl: 'js/common/component/notification/notification.view.html'
    });
})();