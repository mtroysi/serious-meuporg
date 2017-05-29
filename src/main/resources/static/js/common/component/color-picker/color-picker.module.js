(function() {
    'use strict';

    angular.module('hello')
        .directive("colorPicker", function(constant) {
            return {
                restrict: "E",
                controllerAs: 'ctrl',
                templateUrl: 'js/common/component/color-picker/color-picker.view.html',
                require: 'ngModel',
                scope: {
                    ngModel: '='
                },
                link: function($scope, element, attrs, ngModel) {

                    $scope.colors = constant.color;

                    $scope.setColor = function(color) {
                        ngModel.$setViewValue(color);
                    };
                }
            };
        });
})();