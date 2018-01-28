(function () {
    'use strict';

    angular.module('dacaApp')

        .component('compareDatasets', {
            replace: true,
            bindings: {
                dataset: "="
            },

            templateUrl: "app/components/compare-datasets/compare-datasets.html",
            controller: 'CompareDatasetsCtrl',
            controllerAs: 'vm'
        });
})();
