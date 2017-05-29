/**
 * Created by Sara on 18/05/17.
 */

(function() {
    'use strict';

    /** @ngInject */
    angular.module('hello')
        .service('UserWS', function($http, constant) {
            var svc = this;

            svc.getUser = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id);
            };

            svc.getAllUser = function() {
                return $http.get(constant.BASE_URI + '/user/list');
            };

            svc.editUser = function(user) {
                return $http.put(constant.BASE_URI + '/user', user);
            };
            svc.getStats = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id + '/stats');
            }

            svc.getRankin = function(id) {
                return $http.get(constant.BASE_URI + '/user/' + id + '/rankin');
            }
            svc.getTopUsers = function() {
                return $http.get(constant.BASE_URI + '/user/top10');
            }

            
            return svc;
        })
})();