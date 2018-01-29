(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('UploadDatasetCtrl', UploadDatasetCtrl);


    UploadDatasetCtrl.$inject = ['$http', '$timeout'];

    function UploadDatasetCtrl($http, $timeout) {
        let vm = this;
        let UPLOAD_DATASET_URL = "http://localhost:1995/upload/{datasetName}";

        vm.datasetsInProgress = [];

        vm.clearDataset = function() {
            vm.datasetName = "";
            vm.fileUrl = "";
        };

        vm.addDataset = function() {
            if(!vm.fileUrl && !vm.filePath) {
                alert("Insert URL or local path to desired file.");
                return;
            }
            if(vm.fileUrl && vm.filePath) {
                alert("Insert only one of the URL or local path.");
                return;
            }
            if(vm.fileUrl) {
                uploadDatasetUrl(vm.datasetName, vm.fileUrl).then(function success(response) {
                    vm.datasetsInProgress.push({
                        'name': vm.datasetName,
                        'url': vm.fileUrl,
                        'progress': Math.random() * 100.0
                    });
                    vm.clearDataset();
                }, function error(response) {
                    alert("Error!\n" + response);
                });
            } else {
                uploadDatasetPath(vm.datasetName, vm.filePath[0]).then(function success(response) {
                    vm.datasetsInProgress.push({
                        'name': vm.datasetName,
                        'url': vm.filePath[0].name,
                        'progress': Math.random() * 100.0
                    });
                    vm.clearDataset();
                }, function error(response) {
                    alert("Error!\n" + response);
                });
            }
        };

        vm.removeDatasetInProgress = function(dataset) {
            for(let i = 0; i < vm.datasetsInProgress.length; i++) {
                if(vm.datasetsInProgress[i].name === dataset.name) {
                    vm.datasetsInProgress.splice(i, 1);
                    break;
                }
            }
        };

        vm.openInputFile = function(ev) {
            ev.stopPropagation();
            $timeout(function() {
                document.querySelector('#input-file-id').click();
            }, 0);
        };

        function uploadDatasetUrl(datasetName, fileUrl) {
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

        function uploadDatasetPath(datasetName, file) {
            let formData = new FormData();
            formData.set("file", file);

            return $http({
                method: "PUT",
                url: UPLOAD_DATASET_URL.replace("{datasetName}", datasetName),
                data: formData,
                headers: {
                    'Content-Type': undefined
                }
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }
    }
})();
