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
        let NAMESPACES_URL = "http://localhost:1994/namespace";
        let NAMESPACE_STATS_URL = "http://localhost:1994/namespace/stats/{datasetName}";

        vm.$onInit = function() {
            getNamespaces().then(function success(response) {
                vm.datasets = [];
                for(let i = 0 ; i < response.data.length; i++) {

                    getNamespaceStats(response.data[i]).then(function success(response) {
                        vm.datasets.push({
                            'name': response.data["namespaceID"],
                            'size': response.data["size"] + " bytes",
                            'numberOfNodes': response.data["nrNodes"],
                            'numberOfBlankNodes': response.data["nrBlankNodes"],
                            'numberOfTriples': response.data["nrTriples"],

                            'numberOfTypes': response.data["nrType"],
                            'numberOfURINodes': response.data["nrURINodes"],
                            'numberOfLiterals': response.data["nrLiterals"],

                            'maxDegree': response.data["maxDegree"],
                            'maxInDegree': response.data["maxInDegree"],
                            'maxOutDegree': response.data["maxOutDegree"]
                        });
                    }, function error(response) {
                        alert("Couldn't get namespace stats for: ", response.data[i]);
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
                url: NAMESPACES_URL
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }

        function getNamespaceStats(datasetName) {
            return $http({
                method: "GET",
                url: NAMESPACE_STATS_URL.replace("{datasetName}", datasetName)
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }
    }
})();
