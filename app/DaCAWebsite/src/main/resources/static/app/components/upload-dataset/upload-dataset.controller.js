(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('UploadDatasetCtrl', UploadDatasetCtrl);


    UploadDatasetCtrl.$inject = ['$http'];

    function UploadDatasetCtrl($http) {
        let vm = this;
        let UPLOAD_DATASET_URL = "http://localhost:1995/upload/{datasetName}";

        vm.datasetsInProgress = [];

        vm.clearDataset = function() {
            vm.datasetName = "";
            vm.fileUrl = "";
        };

        vm.addDataset = function() {
            uploadDataset(vm.datasetName, vm.fileUrl).then(function success(response) {
                console.log("addDataset", response);
                vm.datasetsInProgress.push({
                    'name': vm.datasetName,
                    'url': vm.fileUrl,
                    'progress': Math.random() * 100.0
                });
                vm.clearDataset();
            }, function error(response) {
                alert("Error!\n" + response);
            });
        };

        vm.removeDatasetInProgress = function(dataset) {
            for(let i = 0; i < vm.datasetsInProgress.length; i++) {
                if(vm.datasetsInProgress[i].name === dataset.name) {
                    vm.datasetsInProgress.splice(i, 1);
                    break;
                }
            }
        };

        function uploadDataset(datasetName, fileUrl) {
            return $http({
                method: "POST",
                url: UPLOAD_DATASET_URL.replace("{datasetName}", datasetName),
                params: {
                    'url': fileUrl
                }
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }
    }
})();
