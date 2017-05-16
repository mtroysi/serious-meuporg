/**
 * Created by Morgane TROYSI on 16/05/17.
 */


(function () {
    'use strict';

    /** @ngInject */
    function ComponentUsersInputController(CommonUsersInputService) {
        var ctrl = this;
        // ctrl.users = [];
        console.log(ctrl);

        ctrl.loadUsers = function (query) {
            return CommonUsersInputService.loadUsers(query);
        };
    }


    angular.module('hello')
        .component('usersInput', {
            controller: ComponentUsersInputController,
            controllerAs: 'ctrl',
            templateUrl: 'js/common/component/users-input/users.input.view.html',
            bindings: {
                key: '='
            }
        });
})();