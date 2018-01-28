(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('SingleDatasetCtrl', SingleDatasetCtrl);


    SingleDatasetCtrl.$inject = [];

    function SingleDatasetCtrl() {
        let vm = this;

        vm.selectGraph = function(ev, graph) {
            vm.selectedGraph = graph;
        };
    }
})();
