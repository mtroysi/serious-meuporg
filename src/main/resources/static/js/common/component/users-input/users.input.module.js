/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentUsersInputController(CommonUsersInputService) {
        var ctrl = this;

        /**
         * Récupère les utilisateurs dont le nom contient la chaîne en paramètre
         * @param query chaîne à chercher
         */
        ctrl.loadUsers = function (query) {
            return CommonUsersInputService.loadUsers(query, ctrl.withCurrent, ctrl.boardId);
        };
    }


    angular.module('hello')
        .component('usersInput', {
            controller: ComponentUsersInputController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/users-input/users.input.view.html',
            bindings: {
                users: '=',
                withCurrent: '=',
                boardId: '=?'
            }
        });
})();