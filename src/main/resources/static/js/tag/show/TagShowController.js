/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TaskShowController', function (TagShowService, $stateParams) {
            var ctrl = this;

            ctrl.init = function () {
                ctrl.tag = {};
                ctrl.showTag($stateParams.id);
            };

            ctrl.showTag = function (id) {
                TagShowService.showTag(id).then(function (data) {
                    ctrl.tag = data;
                });
            };


            ctrl.init();
        })
})();