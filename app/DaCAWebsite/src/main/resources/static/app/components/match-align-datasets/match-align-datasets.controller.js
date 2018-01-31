(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('MatchAlignDatasetsCtrl', MatchAlignDatasetsCtrl);


    MatchAlignDatasetsCtrl.$inject = ['$http'];

    function MatchAlignDatasetsCtrl($http) {
        let vm = this;

        let NAMESPACES_URL = "http://localhost:1994/namespace";
        let MATCH_ALIGN_URL = "http://localhost:1998/matchalign/{datasetName1}/{datasetName2}";

        vm.$onInit = function() {
            getNamespaces().then(function success(response) {
                vm.datasets = [];
                for(let i = 0 ; i < response.data.length; i++) {
                    vm.datasets.push({
                        'name': response.data[i]
                    });
                }
            }, function error(response) {
                alert("Couldn't get namespaces");
            })
        };

        vm.submit = function() {
            matchAlign(vm.firstDataset, vm.secondDataset).then(function success(response) {
                vm.alignments = response.data;
            }, function error(response) {
                alert("Couldn't get triples.");
            })
        }

        function matchAlign(dataset1, dataset2) {
            return $http({
                method: "GET",
                url: MATCH_ALIGN_URL.replace("{datasetName1}", dataset1.name)
                    .replace("{datasetName2}", dataset2.name)
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }

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
    }
})();
