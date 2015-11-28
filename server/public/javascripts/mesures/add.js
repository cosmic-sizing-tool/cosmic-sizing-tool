"use strict"

mesuresconfig.registerModule('mesures_add');

angular
  .module('mesures_add')
  .controller('MesuresAddController',[
    '$scope', '$window', '$http', 'jQuery',
    function ($scope, $window, $http, jQuery) {

      $scope.layers = [{
      	name: "Couche",
      	processes: [{
      		ref: "1",
      		name: "Processus",
      		movements: [{
      			ref: "1",
      			name: "Data",
      			movements: "ERX",
      			comment: "commentaire",
      			isTriggeringEntry: true
      		}]
      	}]
      }];

      $scope.save = function () {
      	$http.post('/mesures/savetemp', $scope.layers)
      		.success(function (data, status) {
      			console.log(data);
      			console.log(status);
      		})
      		.error(function (data, status) {
      			console.log(data);
      			console.log(status);
      		});
      };

      $scope.addLine = function (process, index) {
        if(process.movements.length = (index + 1)) {
          process.movements.push({
            ref: "",
            name: "",
            movements: "",
            comment: "",
            isTriggeringEntry: false
          })
        }
        console.log(process);
        console.log(index);
      };

      $scope.$watch('layers', function() { $scope.save(); }, true);
    }
  ])