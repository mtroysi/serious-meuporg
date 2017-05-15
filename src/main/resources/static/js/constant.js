/**
 * Created by Morgane TROYSI on 28/04/2017.
 */

(function () {
    'use strict';

    angular
        .module('hello')
        .constant('constant', {
            'BASE_URI': '/api',
            'priority': {
                'URGENT_IMPORTANT': 'Urgent et important',
                'URGENT_NOT_IMPORTANT':'Pas urgent et important',
                'NOT_URGENT_IMPORTANT':'Urgent et pas important',
                'NOT_URGENT_NOT_IMPORTANT':'Pas urgent et pas important'
            }
        });
})();