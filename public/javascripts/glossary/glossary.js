angular.module('glossaryModule', [])
    .controller('glossaryCtrl', ['$scope', '$http',
        function ($scope, $http) {
            'use strict';

            $scope.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

            $scope.activeLetters = [];

            $scope.loadGlossary = function (lang) {
                var glossaryfile = "assets/json/glossary_" + lang + ".json";
                $http.get(glossaryfile).then(function (success) {
                    $scope.filteredGlossary = $scope.glossary = success.data;
                    $scope.getActiveLetters($scope.filteredGlossary);
                });
            };

            $scope.loadGlossary("fr");

            var notExist = function (letter) {
                return $scope.activeLetters.indexOf(letter) == -1;
            };

            $scope.getActiveLetters = function (glossary) {
                angular.forEach(glossary, function (entry) {
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
                if (letter) {
                    angular.forEach($scope.glossary, function (entry) {
                        if (angular.equals(entry.term[0].toUpperCase(), letter)) {
                            filteredGlossary.push(entry);
                        }
                    });

                    $scope.filteredGlossary = filteredGlossary;

                } else {
                    $scope.filteredGlossary = $scope.glossary;
                }
            };

            $scope.searchText1 = null;
            $scope.searchText2 = null;
            $scope.optRadio1 = null;

            $scope.filter = function () {
                $scope.filtered = true;
            };

            $scope.clear = function () {
                $scope.filtered = false;
                $scope.searchText1 = null;
                $scope.searchText2 = null;
                $scope.optRadio1 = null;
            };


            $http.get('assets/json/ressources_en.json').then(function (ressources) {
                $scope.labels = ressources.data;
            });


        }]);