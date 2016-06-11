angular.module('glossaryModule', [])
    .controller('glossaryCtrl', ['$scope', '$http',
        function ($scope, $http) {
            'use strict';

            $scope.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

            $scope.exist = true;

            $scope.searchText1 = null;
            $scope.searchText2 = null;
            $scope.searchText3 = null;
            $scope.optRadio1 = null;
            $scope.optRadio2 = null;


            $scope.filter = function () {
                $scope.filtered = true;
            };

            $scope.clear = function () {
                $scope.filtered = false;
                $scope.searchText1 = null;
                $scope.searchText2 = null;
                $scope.searchText3 = null;
                $scope.optRadio1 = null;
                $scope.optRadio2 = null;
            };


            $http.get('assets/json/ressources_en.json').then(function (ressources) {
                $scope.labels = ressources.data;
            });


            $http.get('assets/json/glossary_fr.json').then(function (glossary) {
                $scope.glossary = glossary.data;
            });


        }]);