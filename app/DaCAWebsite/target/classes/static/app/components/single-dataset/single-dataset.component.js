(function () {
    'use strict';

    angular.module('dacaApp')

        .component('singleDataset', {
            replace: true,
            bindings: {
                dataset: "="
            },

            templateUrl: "app/components/single-dataset/single-dataset.html",
            controller: 'SingleDatasetCtrl',
            controllerAs: 'vm'
        });
})();
