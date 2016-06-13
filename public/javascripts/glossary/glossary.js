angular.module('glossaryModule', ['ngSanitize'])
    .controller('glossaryCtrl', ['$scope', '$http', 'filterFilter',
        function ($scope, $http, filterFilter) {
            'use strict';

            $scope.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

            $scope.activeLetters = [];

            $scope.optFilter = "and";

            var initGlossaryToDisplay = function () {
                $scope.glossary = $scope.activeFilter ? $scope.filteredGlossary : $scope.loadedGlossary;
                $scope.getActiveLetters();
            };

            var notExist = function (letter) {
                return $scope.activeLetters.indexOf(letter) == -1;
            };

            $scope.loadGlossary = function (lang) {
                var glossaryfile = "assets/json/glossary_" + lang + ".json";
                $http.get(glossaryfile).then(function (success) {
                    $scope.loadedGlossary = success.data;
                    initGlossaryToDisplay();
                });
            };

            $scope.loadGlossary("en");

            $scope.getActiveLetters = function () {
                $scope.activeLetters = [];
                angular.forEach($scope.glossary, function (entry) {
                    var firstLetter = entry.term[0].toUpperCase();
                    if (notExist(firstLetter)) {
                        $scope.activeLetters.push(firstLetter);
                    }
                });
            };

            $scope.inactive = function (letter) {
                return notExist(letter);
            };

            $scope.filterByLetter = function (letter) {
                var filteredGlossary = [];
                initGlossaryToDisplay();

                if (letter) {
                    angular.forEach($scope.glossary, function (entry) {
                        if (angular.equals(entry.term[0].toUpperCase(), letter)) {
                            filteredGlossary.push(entry);
                        }
                    });

                    $scope.glossary = filteredGlossary;

                } else {
                    initGlossaryToDisplay();
                }
            };

            $scope.filter = function () {
                $scope.activeFilter = $scope.searchText1 || $scope.searchText2;

                if ($scope.searchText1 && $scope.searchText2) {
                    if ($scope.optFilter == "and") {
                        $scope.filteredGlossary = filterFilter(filterFilter($scope.loadedGlossary, $scope.searchText1), $scope.searchText2);
                    } else {
                        var firstResults = filterFilter($scope.loadedGlossary, $scope.searchText1);
                        var secondtResults = filterFilter($scope.loadedGlossary, $scope.searchText2);

                        angular.forEach(firstResults, function(result) {
                            var index = secondtResults.indexOf(result);
                            if (index >= 0) {
                                secondtResults.splice(index,1);
                            }
                        });
                        $scope.filteredGlossary = firstResults.concat(secondtResults);
                    }

                    initGlossaryToDisplay();

                } else if ($scope.searchText1 || $scope.searchText2) {
                    var searchText = $scope.searchText1 ? $scope.searchText1 : $scope.searchText2;
                    $scope.filteredGlossary = filterFilter($scope.loadedGlossary, searchText);
                    initGlossaryToDisplay();
                }
            };

            $scope.clearFilter = function () {
                $scope.filteredGlossary = null;
                $scope.activeFilter = false;
                $scope.searchText1 = null;
                $scope.searchText2 = null;
                $scope.optFilter = "and";
                initGlossaryToDisplay();
            };

            // Chargement des labels    
            $http.get('assets/json/ressources_en.json').then(function (ressources) {
                $scope.labels = ressources.data;
            });


        }]);