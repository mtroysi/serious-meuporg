/**
 * Created by Morgane TROYSI on 30/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    function ComponentSearchBarController($state, CommonSearchBarService) {
        var ctrl = this;

        /**
         * Retourne la liste des utilisateurs  et des tâches dont le nom contiennent la chaîne en paramètre.
         * @param query chaîne à chercher
         * @returns {HttpPromise}
         */
        ctrl.searchUsersAndTasks = function(query) {
            return CommonSearchBarService.searchUsersAndTasks(query);
        };

        /**
         * Redirige vers le joueur ou la tâche sélectionnée
         * @param $item
         * @param $model
         * @param $label
         */
        ctrl.onSelect = function($item, $model, $label) {
            $('body').click();
            if ($item.type === "USER") {
                $state.go('app.profil', { idUser: $item.id });
            } else {
                $state.go('app.board-preview', { idtask: $item.id });
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