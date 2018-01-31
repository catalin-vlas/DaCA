(function () {
    'use strict';

    angular.module('dacaApp')

        .component('matchAlignDatasets', {
            replace: true,
            bindings: {
                dataset: "="
            },

            templateUrl: "app/components/match-align-datasets/match-align-datasets.html",
            controller: 'MatchAlignDatasetsCtrl',
            controllerAs: 'vm'
        });
})();
