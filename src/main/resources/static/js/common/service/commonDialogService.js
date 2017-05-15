(function() {
    'use strict';

    var helloApp = angular.module('hello');

    /** @ngInject */
    helloApp.service('CommonDialogService', function(HomeWS) {

        var svc = {};

        svc.confirmation = function(message, onConfirm, onCancel, id, title, ok, cancel) {
            svc.confirm({
                message: message,
                onConfirm: onConfirm,
                onCancel: onCancel,
                id: id,
                title: title,
                ok: ok,
                cancel: cancel
            });
        };

        svc.confirm = function(opts) {
            opts = (opts || {});
            const defaults = {
                title: 'Confirmation',
                message: 'Do you wish to proceed?',
                ok: 'Valider',
                cancel: 'Annuler',
                id: 'messageModal'
            };

            const options = $.extend(defaults, opts);

            svc.message({
                type: 'confirm',
                title: options.title,
                message: options.message,
                id: options.id,
                actions: [{
                        name: options.ok,
                        css: 'btn-success btn-lg pull-right',
                        callback: options.onConfirm
                    },
                    {
                        name: options.cancel,
                        css: 'btn-warning pull-left',
                        callback: options.onCancel
                    }
                ]
            });
        };

        svc.error = function(message, title, callback) {
            svc.message({
                type: 'error',
                message: message,
                title: title,
                actions: [{
                    name: 'Valider',
                    css: 'btn-success btn-lg',
                    callback: callback
                }]
            });
        };

        svc.warning = function(message, title, callback) {
            svc.message({
                type: 'warning',
                message: message,
                title: title,
                actions: [{
                    name: 'Valider',
                    css: 'btn-success btn-lg',
                    callback: callback
                }]
            });
        };

        svc.info = function(message, title, callback) {
            svc.message({
                type: 'info',
                message: message,
                title: title,
                actions: [{
                    name: 'Valider',
                    css: 'btn-success btn-lg',
                    callback: callback
                }]
            });
        };

        svc.message = function(opts) {
            opts = (opts || {});
            const defaults = {
                type: 'confirm',
                message: 'Mon message.',
                actions: [{
                    name: 'Valider',
                    css: 'btn-success btn-lg'
                }]
            };

            // remove any popup left behind
            $('#confirm-dialog')
                .remove();

            const options = $.extend(defaults, opts);

            this.action = null;

            let suffix = 'confirm';
            let title = 'Confirmation';
            let id = 'messageModal';
            if (options.type === 'error') {
                suffix = 'error';
                title = 'Erreur';
            } else if (options.type === 'warning') {
                suffix = 'warning';
                title = 'Avertissement';
            } else if (options.type === 'info') {
                suffix = 'information';
                title = 'Information';
            }

            title = options.title || title;
            id = options.id || id;

            let buttons = '';
            for (let i = 0; i < options.actions.length; i++) {
                const action = options.actions[i];
                buttons += '<button type="button" id="action' + i + '" class="btn ' + action.css + ' btn-top" >' + action.name + '</button>';
            }

            const $dialog = $(
                '<div class="modal fade" id="' +id+ '" tabindex="-1" role="dialog" aria-labelledby="titleModal"> \
                        <div class="modal-dialog" role="document"> \
                            <div class="modal-content"> \
                                <div class="modal-header modal-header-' +suffix+ '"> \
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> \
                                    <h5 class="modal-title" id="titleModal">' +title+ '</h5> \
                                </div> \
                                <div class="modal-body text-center clearfix"> \
                                    <h4 class="wrap">' +options.message+ '</h4> \
                                    ' + buttons+ ' \
                                </div> \
                            </div> \
                        </div> \
                    </div>'
            )
            .appendTo('body');

            // override some features
            $dialog.on('hidden.bs.modal', function() {
                $dialog.remove();
                if (angular.isDefined(this.action) && this.action !== null && angular.isFunction(this.action.callback)) {
                    $rootScope.$apply();
                    this.action.callback();
                }
            }.bind(this));
            $dialog.modal('show');

            for (let i = 0; i < options.actions.length; i++) {
                const action = options.actions[i];
                $dialog.find('#action' + i)
                    .click(function() {
                        this.action = action;
                        $dialog.modal('hide');
                    }.bind(this));
            }
        };

        return svc;

    });
})();