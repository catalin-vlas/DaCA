(function () {
    'use strict';

    angular.module('dacaApp')

        .component('main', {
            replace: true,
            bindings: {},

            templateUrl: "app/components/main/main.html",
            controller: 'MainCtrl',
            controllerAs: 'vm'
        });
})();
