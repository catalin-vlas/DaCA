(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('UploadDatasetCtrl', UploadDatasetCtrl);


    UploadDatasetCtrl.$inject = [];

    function UploadDatasetCtrl() {
        let vm = this;

        vm.datasetsInProgress = [];

        vm.clearDataset = function() {
            vm.datasetName = "";
            vm.fileUrl = "";
        };

        vm.addDataset = function() {
            vm.datasetsInProgress.push({
                'name': vm.datasetName,
                'url': vm.fileUrl,
                'progress': Math.random() * 100.0
            });
            vm.clearDataset();
        };

        vm.removeDatasetInProgress = function(dataset) {
            for(let i = 0; i < vm.datasetsInProgress.length; i++) {
                if(vm.datasetsInProgress[i].name === dataset.name) {
                    vm.datasetsInProgress.splice(i, 1);
                    break;
                }
            }
        };
    }
})();
