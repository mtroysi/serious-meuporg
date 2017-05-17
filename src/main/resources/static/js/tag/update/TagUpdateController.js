/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TagUpdateController', function (TagShowService, TagUpdateService, $stateParams) {
            var ctrl = this;

            ctrl.init = function () {
                ctrl.tag = {};
                TagShowService.showTag($stateParams.id).then(function (data) {
                    ctrl.tag = data;
                });
            };

            ctrl.updateTag = function () {
                TagUpdateService.updateTag($stateParams.id, ctrl.tag).then(function (data) {
                    ctrl.tag = data;
                });
            };
            ctrl.deleteTag = function () {
                TagUpdateService.deleteTag($stateParams.id);
                ctrl.tag = {};
            };

            ctrl.init();
        })
})();