/**
 * Created by Mathieu on 6/11/2016.
 */

"use strict"

measuresconfig.registerModule('measures_add');

angular
    .module('measurers')
    .controller('MeasurersController',[
        '$scope', '$http',
        function ($scope, $http) {
            var SearchServiceRoute = '/measurers/search';

            $scope.model = {
                measurers : [],
                filteredMeasurers : [],
                filters : {
                    method : null,
                    country : null,
                    state : null,
                    city : null
                },
                methods : [],
                countries : [],
                states : [],
                cities : [],
                hasError : false
            };

            $scope.actions = {
                constructServiceUrl : function(){
                    return SearchServiceRoute + '?method=' + $scope.model.filters.method ? $scope.model.filters.method : "" +
                                                '&country' + $scope.model.filters.country ? $scope.model.filters.country : "" +
                                                '&state' + $scope.model.filters.state ? $scope.model.filters.state : "" +
                                                '&city' + $scope.model.filters.city ? $scope.model.filters.city : "";
                },
                search : function(){
                    $http.get($scope.actions.constructServiceUrl)
                        .then(function(data){
                            //success
                            $scope.model.measurers = data;
                            $scope.model.hasError = false;
                        }, function(){
                            //error
                            $scope.measurers = [];
                            $scope.model.hasError = true;
                        });
                }
            };

            var update = {
                cities: function(measurers){
                    //get cities info for all measurer.city
                    if($scope.model.filters.country && $scope.model.filters.state){
                        $scope.cities = _.sortedUniqBy(measurers, function(measurer){
                            return measurer.city;
                        });
                    }
                },
                filteredMeasurers : function(city){
                    $scope.filteredMeasurers = _.filter($scope.measurers, function(measurer){
                       return measurer.city == city;
                    });
                }
            };

            //load methods
            //loadh countries
            //load states

            $watch('model.measurers', function(newValue, oldValue){
                if(newValue != oldValue && newValue != []){
                    update.cities(newValue);
                }
            });

            $watch('model.filters.city', function(newValue, oldValue){
                if(newValue != oldValue){
                    update.filteredMeasurers(newValue);
                }
            });
        }
    ]);