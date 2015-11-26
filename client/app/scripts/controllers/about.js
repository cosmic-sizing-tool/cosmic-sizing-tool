'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('AboutCtrl', function (aboutService) {

    aboutService.getTeam().then(function (data) {
        this.team = data;
    }.bind(this));

});
