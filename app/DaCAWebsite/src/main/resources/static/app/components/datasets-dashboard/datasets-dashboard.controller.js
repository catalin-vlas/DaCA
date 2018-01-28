(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('DatasetsDashboardCtrl', DatasetsDashboardCtrl)
        .config(function($mdThemingProvider) {
            $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
        });


    DatasetsDashboardCtrl.$inject = ['$http'];

    function DatasetsDashboardCtrl($http) {
        let vm = this;
        let STATS_NAMESPACES_URL = "http://localhost:1997/stats/namespaces";

        vm.$onInit = function() {
            getNamespaces().then(function success(response) {
                vm.datasets = [];
                for(let i = 0 ; i < response.data.length; i++) {
                    vm.datasets.push({
                        'name': response.data[i],
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
                    });
                }
            }, function error(response) {
                alert("Couldn't get namespaces");
            })
        };

        vm.setIndividualDataset = function(dataset) {
            vm.onSetIndividualDataset({'dataset': dataset});
        };

        function getNamespaces() {
            return $http({
                method: "GET",
                url: STATS_NAMESPACES_URL
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }
    }
})();
