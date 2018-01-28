(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('DatasetsDashboardCtrl', DatasetsDashboardCtrl)
        .config(function($mdThemingProvider) {
            $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
        });


    DatasetsDashboardCtrl.$inject = [];

    function DatasetsDashboardCtrl() {
        let vm = this;

        vm.datasets = [
            {
                'name': 'Species',
                'size': '4MB',
                'numberOfGraphs': 5,
                'numberOfNodes': 1337,
                'numberOfTriples': 4500,
                'maximumTriples': 21,
                'graphs': [
                    {'name': 'Graph A'},
                    {'name': 'Graph B'},
                    {'name': 'Graph C'},
                    {'name': 'Graph D'}
                ]
            },
            {
                'name': 'Antibiotics',
                'size': '17MB',
                'numberOfGraphs': 2,
                'numberOfNodes': 666013,
                'numberOfTriples': 10000007,
                'maximumTriples': 15,
                'graphs': [
                    {'name': 'Graph 1'},
                    {'name': 'Graph 2'},
                    {'name': 'Graph 3'},
                    {'name': 'Graph 5'},
                    {'name': 'Graph 7'},
                    {'name': 'Graph 4'}
                ]
            }
        ];

        vm.setIndividualDataset = function(dataset) {
            vm.onSetIndividualDataset({'dataset': dataset});
        };
    }
})();
