var myForm = angular.module('admissionForm', []);

myForm.controller('RegistrationCtrl', ['$scope', function ($scope) {

    $scope.register = function () {
//        $scope.message = 'qc study ' + $scope.user.study + ' miic ' + $scope.user.miic;
        var userInfo = $scope.user;
        console.log(userInfo);
    };


}]);