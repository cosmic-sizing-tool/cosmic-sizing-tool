/**
 * Created by Mathieu on 6/11/2016.
 */

"use strict"

angular
    .module('app')
    .controller('MeasurersController',[
        '$scope', '$http', '$translate',
        function ($scope, $http, $translate) {
            var SearchServiceRoute = '/measurers/search';

            //TODO: Bind country and regions on DB
            // Countries have a region with code "" that defines the select's default option. For US it is "All States"
            var hardcodedCountryRegionJson = {"":{"name":"All Countries", "divisions":{"":"Regions"}},"CA":{"name":"Canada","divisions":{"":"All Provinces", "CA-AB":"Alberta","CA-BC":"British Columbia","CA-MB":"Manitoba","CA-NB":"New Brunswick","CA-NF":"Newfoundland","CA-NS":"Nova Scotia","CA-ON":"Ontario","CA-PE":"Prince Edward Island","CA-QC":"Quebec","CA-SK":"Saskatchewan","CA-NT":"Northwest Territories","CA-YT":"Yukon Territory"}}, "US":{"name":"United States","divisions":{"":"All States","US-AL":"Alabama","US-AK":"Alaska","US-AZ":"Arizona","US-AR":"Arkansas","US-CA":"California","US-CO":"Colorado","US-CT":"Connecticut","US-DE":"Delaware","US-FL":"Florida","US-GA":"Georgia","US-HI":"Hawaii","US-ID":"Idaho","US-IL":"Illinois","US-IN":"Indiana","US-IA":"Iowa","US-KS":"Kansas","US-KY":"Kentucky","US-LA":"Louisiana","US-ME":"Maine","US-MD":"Maryland","US-MA":"Massachusetts","US-MI":"Michigan","US-MN":"Minnesota","US-MS":"Mississippi","US-MO":"Missouri","US-MT":"Montana","US-NE":"Nebraska","US-NV":"Nevada","US-NH":"New Hampshire","US-NJ":"New Jersey","US-NM":"New Mexico","US-NY":"New York","US-NC":"North Carolina","US-ND":"North Dakota","US-OH":"Ohio","US-OK":"Oklahoma","US-OR":"Oregon","US-PA":"Pennsylvania","US-RI":"Rhode Island","US-SC":"South Carolina","US-SD":"South Dakota","US-TN":"Tennessee","US-TX":"Texas","US-UT":"Utah","US-VT":"Vermont","US-VA":"Virginia","US-WA":"Washington","US-WV":"West Virginia","US-WI":"Wisconsin","US-WY":"Wyoming","US-DC":"District of Columbia"}}};

            //The sorting makes sure to keep the entry with code == "" on top
            function countriesFromJSON(json){
                var countriesKeyValuePairs = _.toPairs(json);
                var countries = _.map(countriesKeyValuePairs, function(pair){return {code: pair[0], name: pair[1].name, regions: regionsFromJSON(pair[1].divisions, pair[0])}});
                return _.sortBy(countries, function(country){
                    if(country.code == ""){
                        return "";
                    }
                    return country.name;
                });
            };

            //The sorting makes sure to keep the entry with code == "" on top
            function regionsFromJSON(json, country){
                var regionKeyValuePairs = _.toPairs(json);
                var regions = _.map(regionKeyValuePairs, function(pair){
                   return {code: pair[0], name: pair[1]};
                });
                return _.sortBy(regions, function(region){
                    if(region.code==""){
                        return "";
                    }
                    return region.name;
                });
            };

            $scope.model = {
                measurers : [],
                filteredMeasurers : [],
                filters : {
                    method : null,
                    country : null,
                    state : null,
                    city : null
                },
                //TODO: Bind methods on measurement methods in DB
                methods : [ {code: "", name: "All Methods"}, {code: "COSMIC", name: "COSMIC"}, {code: "IFPUG", name: "IFPUG"}, {code: "NESMA", name: "NESMA"}, {code: "FISMA", name: "FISMA"}, {code: "Mark II", name: "Mark II"}],
                countries : [],
                states : [],
                cities : [],
                hasError : false
            };

            $scope.actions = {
                constructServiceUrl : function(){
                    return SearchServiceRoute;
                },
                search : function(){
                    //TODO: Add a condition to not execute the get if the filters didn't change
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
                },
                resetRegions : function(){
                    $scope.model.filters.state = "";
                },
                regions: function(countryCode){
                    $scope.model.states = regionsFromJSON(hardcodedCountryRegionJson[countryCode].divisions);
                }
            };

            //load countries & states
            $scope.model.countries = countriesFromJSON(hardcodedCountryRegionJson);
            $scope.model.states = regionsFromJSON(hardcodedCountryRegionJson[""].divisions);

            $scope.$watch('model.filters.country', function(newValue, oldValue){
                if(newValue != oldValue || newValue == ""){
                    update.resetRegions();
                    update.regions(newValue);
                }
            });

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