/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    function ComponentSearchBarController($state, CommonSearchBarService) {
        var ctrl = this;

        ctrl.searchUsersAndTasks = function (query) {
            return CommonSearchBarService.searchUsersAndTasks(query);
        };
        
        ctrl.onSelect = function ($item, $model, $label) {
            if($item.type === "USER") {
                $state.go('app.profil', { idUser: $item.id });
            } else {
                // $state.go('app.board-preview', { idtask: $item.id });
            }
        };
    }


    angular.module('hello')
        .component('searchBar', {
            controller: ComponentSearchBarController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/search-bar/search.bar.view.html'
        });
})();