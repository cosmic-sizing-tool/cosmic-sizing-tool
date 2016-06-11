angular.module('glossaryModule', [])
    .controller('glossaryCtrl', ['$scope', '$http',
        function ($scope, $http) {
            'use strict';

            $scope.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

            $scope.activeLetters = [];

            $scope.exist = function(letter) {
                loadActiveLetters($scope.glossary);
                 return $scope.activeLetters.indexOf(letter) >= 0;
            };

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

            var loadActiveLetters = function (glossary) {
                angular.forEach(glossary, function(entry) {
                    $scope.activeLetters.push(entry.term[0].toUpperCase());
                });
                removeDuplicate($scope.activeLetters);
            };

            var removeDuplicate = function (array) {
                array.sort();
                for (var i = 1; i < array.length; i++) {
                    if (array[i - 1] === array[i])
                        array.splice(i, 1);
                };
            };


            $http.get('assets/json/ressources_en.json').then(function (ressources) {
                $scope.labels = ressources.data;
            });


            $http.get('assets/json/glossary_fr.json').then(function (glossary) {
                $scope.glossary = glossary.data;
            });

            $scope.filterByLetter = function(letter){
    	    	/*if( row.term[0].toUpperCase() === $scope.currentLetter){
    	    	 return row;
    	    	}*/
    	    };
     
    	  $scope.byFirstLetter = function(row){
    	    return row.term[0].toUpperCase() === $scope.currentLetter;	
    	  };
        }]);