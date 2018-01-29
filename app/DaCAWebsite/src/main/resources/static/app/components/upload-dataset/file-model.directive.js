(function(){
    "use strict";
    angular.module("dacaApp")
        .directive("fileModel", function () {
            return {
                require: "ngModel",
                link: function postLink(scope, elem, attrs, ngModel) {
                    elem.on("change", function(e) {
                        let files = elem[0].files;
                        ngModel.$setViewValue(files);
                    })
                }
            }
        })

} ());
