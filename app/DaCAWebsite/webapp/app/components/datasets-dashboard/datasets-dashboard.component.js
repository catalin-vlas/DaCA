(function () {
    'use strict';

    angular.module('dacaApp')

        .component('datasetsDashboard', {
            replace: true,
            bindings: {
                onSetIndividualDataset: '&'
            },

            templateUrl: "app/components/datasets-dashboard/datasets-dashboard.html",
            controller: 'DatasetsDashboardCtrl',
            controllerAs: 'vm'
        });
})();
