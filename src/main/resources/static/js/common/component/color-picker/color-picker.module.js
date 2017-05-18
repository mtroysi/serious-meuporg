(function() {
    'use strict';

    function ComponentColorPickerController(constant) {
        var ctrl = this;

        // Constructor
        ctrl.init = function() {
            ctrl.colors = constant.color;
        };

        ctrl.setColor = function(color) {
            ctrl.ngModel = color;
        };

        ctrl.init();
    }


    angular.module('hello')
        .component('colorPicker', {
            controller: ComponentColorPickerController,
            controllerAs: 'vm',
            templateUrl: 'js/common/component/color-picker/color-picker.view.html',
            bindings: {
                ngModel: '='
            }
        });
})();