/**
 * Created by Morgane TROYSI on 28/04/2017.
 */

(function() {
    'use strict';

    angular
        .module('hello')
        .constant('moment', moment)
        .constant('constant', {
            'BASE_URI': '/api',
            'priority': {
                'URGENT_IMPORTANT': 'Urgent et important',
                'URGENT_NOT_IMPORTANT': 'Pas urgent et important',
                'NOT_URGENT_IMPORTANT': 'Urgent et pas important',
                'NOT_URGENT_NOT_IMPORTANT': 'Pas urgent et pas important'
            },
            'color': ['info', 'primary', 'blue', 'indigo', 'deeppurple', 'purple', 'pink', 'danger', 'teal', 'green',
                'success', 'lime', 'yellow', 'warning', 'orange', 'deeporange', 'midnightblue', 'bluegray',
                'bluegraylight', 'black', 'gray', 'default', 'white', 'brown'
            ],
            'XP': 50
        });
})();