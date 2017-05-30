/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    function ComponentSearchBarController(CommonSearchBarService) {
        var ctrl = this;

        ctrl.searchUsersAndTasks = function (query) {
            return CommonSearchBarService.searchUsersAndTasks(query);
        };
    }


    angular.module('hello')
        .component('searchBar', {
            controller: ComponentSearchBarController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/search-bar/search.bar.view.html'
        });
})();