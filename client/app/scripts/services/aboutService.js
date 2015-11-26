'use strict';

angular.module('clientApp')
    .service('aboutService', function ($http) {

        var getTeam = function () {
            var promise = $http({
                method: 'GET',
                url: 'https://api.github.com/orgs/cosmic-sizing-tool/public_members'
            }).then(function successCallback(response) {
                return response.data;
            });
            return promise
        };

        return {
            getTeam: getTeam,
        }

    });
