/**
 * Created by Mathieu on 6/11/2016.
 */

"use strict"

measurersconfig.registerModule('measurers.search');

angular
    .module('measurers.search')
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
                methods : [ {code: null, name: "All Methods"}, {code: "COSMIC", name: "COSMIC"}, {code: "IFPUG", name: "IFPUG"}, {code: "NESMA", name: "NESMA"}, {code: "FISMA", name: "FISMA"}, {code: "Mark II", name: "Mark II"}],
                countries : [
                    {
                        code: null,
                        name: "All Countries"
                    },
                    {
                    code:  "ca",
                    name: "Canada"
                }],
                states : [
                    {
                        code: null,
                        name: "All states"
                    },
                    {
                    code: "AB",
                    name: "Alberta"
                },
                    {
                        code: "BC",
                        name: "British Columbia"
                    },
                    {
                        code: "MB",
                        name: "Manitoba"
                    },
                    {
                        code: "NB",
                        name: "New Brunswick"
                    },
                    {
                        code: "NL",
                        name: "Newfoundland and Labrador"
                    },
                    {
                        code: "NS",
                        name: "Nova Scotia"
                    },
                    {
                        code: "ON",
                        name: "Ontario"
                    },
                    {
                        code: "PE",
                        name: "Prince Edward Island"
                    },
                    {
                        code: "QC",
                        name: "Quebec"
                    },
                    {
                        code: "SK",
                        name: "Saskatchewan"
                    },
                    {
                        code: "NT",
                        name: "Northwest Territories"
                    },
                    {
                        code: "NU",
                        name: "Nunavut"
                    },
                    {
                        code: "YT",
                        name: "Yukon"
                    }],
                cities : [],
                hasError : false
            };

            $scope.actions = {
                constructServiceUrl : function(){
                    return SearchServiceRoute;
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

            //load countries & states
            //load cities

            $scope.$watch('model.measurers', function(newValue, oldValue){
                if(newValue != oldValue && newValue != []){
                    update.cities(newValue);
                }
            });

            $scope.$watch('model.filters.city', function(newValue, oldValue){
                if(newValue != oldValue){
                    update.filteredMeasurers(newValue);
                }
            });
        }
    ]);