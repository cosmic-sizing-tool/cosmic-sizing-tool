var myForm = angular.module('registrationForm', []);

myForm.controller('RegistrationCtrl', ['$scope', function ($scope) {

    $scope.register = function () {
        var userInfo = $scope.user;
        console.log(userInfo);
    };
}]);