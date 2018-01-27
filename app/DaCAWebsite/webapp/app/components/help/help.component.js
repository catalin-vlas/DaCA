(function () {
    'use strict';

    angular.module('dacaApp')

        .component('help', {
            replace: true,
            bindings: {},

            templateUrl: "app/components/help/help.html",
            controller: 'HelpCtrl',
            controllerAs: 'vm'
        });
})();
