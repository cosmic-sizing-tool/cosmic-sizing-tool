"use strict"

var measuresconfig = {
  app: 'measures',
  dependencies: [],
  registerModule:  function(module, dep) {
    angular.module(module, dep || []);
    angular.module('measures').requires.push(module);
  }
};

angular
  .module('measures', measuresconfig.dependencies)
  .constant('jQuery', $);

angular.element(document).ready(function() {
  angular.bootstrap(document, [measuresconfig.app]);
});