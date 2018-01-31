(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('UploadDatasetCtrl', UploadDatasetCtrl);


    UploadDatasetCtrl.$inject = ['$http', '$timeout'];

    function UploadDatasetCtrl($http, $timeout) {
        let vm = this;
        let UPLOAD_DATASET_URL = "http://localhost:1995/upload/{datasetName}";

        vm.datasetsInProgress = [];
        vm.formats = ["RDF/XML", "RDF/JSON", "RDFa", "JSON-LD", "N-Triples", "Turtle", "N3", "N-Quads"];

        vm.clearDataset = function() {
            vm.datasetName = "";
            vm.fileUrl = "";
            vm.datasetFormat = "";
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
                vm.datasetsInProgress.push({
                    'name': vm.datasetName,
                    'url': vm.fileUrl
                });
                uploadDatasetUrl(vm.datasetName, vm.datasetFormat, vm.fileUrl).then(function success(response) {
                    vm.datasetsInProgress.pop();
                    vm.datasetsInProgress.push({
                        'name': vm.datasetName,
                        'url': vm.fileUrl,
                        'loadingStatus': response.data
                    });
                    console.log("uploadDatasetUrl", response.data);
                    vm.clearDataset();
                }, function error(response) {
                    alert("Error!\n" + response);
                });
            } else {
                uploadDatasetPath(vm.datasetName, vm.datasetFormat, vm.filePath[0]).then(function success(response) {
                    vm.datasetsInProgress.pop();
                    vm.datasetsInProgress.push({
                        'name': vm.datasetName,
                        'url': vm.fileUrl,
                        'loadingStatus': response.data
                    });
                    console.log("uploadDatasetPath", response.data);
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

        function uploadDatasetUrl(datasetName, format, fileUrl) {
            return $http({
                method: "POST",
                url: UPLOAD_DATASET_URL.replace("{datasetName}", datasetName),
                params: {
                    'url': fileUrl,
                    'format': format
                }
            }).then(function success(response) {
                return response;
            }, function error(response) {
                return response;
            });
        }

        function uploadDatasetPath(datasetName, format, file) {
            let formData = new FormData();
            formData.set("file", file);
            formData.set("format", format);

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
