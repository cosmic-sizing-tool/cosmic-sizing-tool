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
      		ref: "",
      		name: "",
      		movements: [{
      			ref: "",
      			name: "",
      			movements: "",
      			comment: "",
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

      $scope.addLine = function (process, index, layer) {
        if(process.movements.length = (index + 1)) {
          var m = process.movements[index];
          if(!m.ref && !m.name && !m.movements && !m.comment) {
            layer.processes.push({
              ref: "",
              name: "",
              movements: [{
                ref: "",
                name: "",
                movements: "",
                comment: "",
                isTriggeringEntry: true
              }]
            });

          } else {
            process.movements.push({
              ref: "",
              name: "",
              movements: "",
              comment: "",
              isTriggeringEntry: false
            });
          }
          
        }
        console.log(process);
        console.log(index);
      };

      $scope.$watch('layers', function() { $scope.save(); }, true);
    }
  ])