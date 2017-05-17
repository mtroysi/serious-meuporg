/**
 * Created by Florentin NOËL on 11/05/17.
 */

(function () {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .controller('TagCreateController', function (TagCreateService, constant) {
            var ctrl = this;

            ctrl.color = constant.color;

            ctrl.init = function () {
                ctrl.tag = {};
            };

            ctrl.createTag = function () {
                TagCreateService.createTag(ctrl.tag).then(function (data) {
                    ctrl.tag = data;
                })
            };

            ctrl.setColor = function (color) {
                ctrl.tag.color = color;
            };

            ctrl.init();
        })
})();