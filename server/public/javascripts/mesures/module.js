"use strict"

var mesuresconfig = {
  app: 'mesures',
  dependencies: [],
  registerModule:  function(module, dep) {
    angular.module(module, dep || []);
    angular.module('mesures').requires.push(module);
  }
};

angular
  .module('mesures', mesuresconfig.dependencies)
  .constant('jQuery', $);

angular.element(document).ready(function() {
  angular.bootstrap(document, [mesuresconfig.app]);
});