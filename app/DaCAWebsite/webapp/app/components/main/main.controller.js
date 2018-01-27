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
            vm.activePage = vm.pages.UPLOAD;
        };

        vm.changePage = function(page) {
            vm.activePage = page;
        }
    }
})();
