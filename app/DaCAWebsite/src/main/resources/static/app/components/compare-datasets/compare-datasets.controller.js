(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('CompareDatasetsCtrl', CompareDatasetsCtrl);


    CompareDatasetsCtrl.$inject = ['$http'];

    function CompareDatasetsCtrl($http) {
        let vm = this;
        let COMPARE_URL = "http://localhost:1999/compare/{operation}/{dataset1}/{dataset2}";
        let EXPORT_URL = "http://localhost:1999/export/{operation}/{dataset1}/{dataset2}?format=n3";
        let NAMESPACES_URL = "http://localhost:1994/namespace";


        vm.operations = ["union", "intersection", "difference", "isomorphism"];

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
            compare(vm.operation, vm.firstDataset, vm.secondDataset).then(function success(response) {
                let triples = response.data;
                d3.select("#svg-body-compare").html("");
                let svg = d3.select("#svg-body-compare").append("svg")
                    .attr("width", 800)
                    .attr("height", 600);

                let force = d3.layout.force().size([800, 600]);

                let graph = triplesToGraph(triples, svg);

                update(graph, force, svg);
            }, function error(response) {
                alert("Couldn't get triples.");
            })
        }

        vm.exportOperation = function() {
            let url = EXPORT_URL.replace("{operation}", vm.operation)
                                .replace("{dataset1}", vm.firstDataset.name)
                                .replace("{dataset2}", vm.secondDataset.name);
            window.location.assign(url);
        }

        function compare(operation, dataset1, dataset2) {
            return $http({
                method: "GET",
                url: COMPARE_URL.replace("{operation}", operation)
                    .replace("{dataset1}", dataset1.name)
                    .replace("{dataset2}", dataset2.name)
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
