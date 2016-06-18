/**
 * Created by Mathieu on 6/11/2016.
 */

"use strict"
var moduleName = 'measurers';

var measurersconfig = {
    app: moduleName,
    dependencies: [],
    registerModule:  function(module, dep) {
        angular.module(module, dep || []);
        angular.module(moduleName).requires.push(module);
    }
};

angular
    .module(moduleName, measurersconfig.dependencies);

angular.element(document).ready(function() {
    angular.bootstrap(document, [measurersconfig.app]);
});
