(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('SingleDatasetCtrl', SingleDatasetCtrl);


    SingleDatasetCtrl.$inject = ['$http'];

    function SingleDatasetCtrl($http) {
        let vm = this;
        let TRIPLES_URL = "http://localhost:1994/triples/{datasetName}";
        let QUERY_URL = "http://localhost:1994/sparql/query";

        vm.$onInit = function() {
            getTriples().then(function success(response) {
                vm.triples = response.data;
                let svg = d3.select("#svg-body").append("svg")
                    .attr("width", 800)
                    .attr("height", 600);

                let force = d3.layout.force().size([800, 600]);

                let graph = triplesToGraph(vm.triples, svg);

                update(graph, force, svg);
            }, function error(response) {
                alert("Couldn't get triples for dataset " + vm.dataset.name + ".");
            });
        };

        vm.submitQuery = function() {
            executeQuery(vm.dataset.name, vm.query).then(function success(response) {
                vm.triples = response.data;
                d3.select("#svg-body").html("");
                let svg = d3.select("#svg-body").append("svg")
                    .attr("width", 800)
                    .attr("height", 600);

                let force = d3.layout.force().size([800, 600]);

                let graph = triplesToGraph(vm.triples, svg);

                update(graph, force, svg);
            }, function error(response) {
                alert("Couldn't get triples for dataset " + vm.dataset.name + ".");
            });
        };

        function getTriples() {
            return $http({
                method: "GET",
                url: TRIPLES_URL.replace("{datasetName}", vm.dataset.name)
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }

        function executeQuery(datasetName, query) {
            return $http({
                method: "GET",
                url: QUERY_URL,
                params: {
                    'namespaceId': datasetName,
                    'query': query
                }
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }
    }
})();
