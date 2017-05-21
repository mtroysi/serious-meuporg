/**
 * Created by Morgane TROYSI on 20/04/2017.
 */
(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.controller('NotificationController', function($scope, $location, $http, NotificationService, CommonNotificationService, AuthenticationService) {
        var ctrl = this;
        ctrl.CommonNotificationService = CommonNotificationService;

        ctrl.init = function() {
            ctrl.listNotification = [];
            ctrl.pager = {};
            ctrl.search = '';
            ctrl.type = 'ALL';

            // The user has just seen all notifications
            NotificationService.readAllNotification(AuthenticationService.getUserId());

            // Call WS for get the list of notification of the user
            NotificationService.getNotificationByUser(AuthenticationService.getUserId()).then(function(data) {
                CommonNotificationService.initListNotification(angular.copy(data));
            });

            /** 
             * Watch the list of menu (list of notification)
             * Variable to watch : listNotifications
             */
            $scope.$watch('this.ctrl.CommonNotificationService.listNotification', function(newValues) {
                ctrl.listNotification = newValues;
                ctrl.setPage(1);
            });
        };

        ctrl.setPage = function(page) {
            // get pager object from service
            ctrl.pager = ctrl.getPager(ctrl.listNotification, page);
        };

        ctrl.getPager = function(listItems, currentPage) {
            // default to first page
            currentPage = currentPage || 1;

            // default page size is 10
            var pageSize = 10;

            //Filter data
            var listItemsTmp = ctrl.filterCustom(angular.copy(listItems));

            // calculate total pages
            var totalItems = listItemsTmp.length;
            var totalPages = Math.ceil(totalItems / pageSize);

            // calculate start and end item indexes
            var startIndex = (currentPage - 1) * pageSize;
            var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);


            // create an array of pages to ng-repeat in the pager control
            var items = listItemsTmp.slice(startIndex, endIndex + 1);

            // return object with all pager properties required by the view
            return {
                totalItems: totalItems,
                currentPage: currentPage,
                totalPages: totalPages,
                listItems: items
            };
        };

        ctrl.filtreType = function(value) {
            ctrl.type = value;
            ctrl.setPage(1);
        };

        ctrl.filterCustom = function(data) {
            console.log(ctrl.type);
            if (ctrl.type != 'ALL') {
                var data = data.filter(function(obj) {
                    return obj.type == ctrl.type;
                });
            }
            if (ctrl.search != '') {
                var data = data.filter(function(obj) {
                    return obj.title.indexOf(ctrl.search) != -1 || obj.content.indexOf(ctrl.search) != -1;
                });
            }
            return data || [];
        }

        ctrl.init();
    })
})();