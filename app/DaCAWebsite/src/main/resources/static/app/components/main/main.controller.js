(function () {
    'use strict';

    angular.module('dacaApp')

        .controller('MainCtrl', MainCtrl);


    MainCtrl.$inject = [];

    function MainCtrl() {
        let vm = this;

        vm.pages = {
            UPLOAD: 'upload',
            DATASETS: {
                DASHBOARD: 'dashboard',
                SINGLE: 'single',
                COMPARE: 'compare',
                MATCH_ALIGN: 'match_align'
            },
            HELP: 'help'
        };

        vm.user = {
            'firstName': 'Tuxy',
            'lastName': 'Pinguinescu'
        };

        vm.$onInit = function() {
            vm.activePage = vm.pages.DATASETS.DASHBOARD;
        };

        vm.changePage = function(page) {
            vm.activePage = page;
        };

        vm.setIndividualDataset = function(dataset) {
            vm.dataset = dataset;
            vm.changePage(vm.pages.DATASETS.SINGLE);
        };
    }
})();
