(function () {
    'use strict';

    angular.module('dacaApp')

        .component('uploadDataset', {
            replace: true,
            bindings: {},

            templateUrl: "app/components/upload-dataset/upload-dataset.html",
            controller: 'UploadDatasetCtrl',
            controllerAs: 'vm'
        });
})();
