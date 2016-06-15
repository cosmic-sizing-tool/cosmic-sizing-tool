(function () {
   'use strict';

angular.module('app', ['pascalprecht.translate', 'registrationForm', 'ngCookies', 'glossaryModule'])
.config(['$translateProvider', function ($translateProvider) {
    translateConfig($translateProvider);
}])
.controller('Ctrl', ['$translate', '$scope', '$cookies', function ($translate, $scope, $cookies) {

    $scope.languages = [
        {id:"en", name:'English'},
        {id:"fr", name:'Francais'}
    ];

    if($cookies.get('lang') == null)
        $scope.selected_language = 'en';
    else
        $scope.selected_language = $cookies.get('lang');

    $translate.use($scope.selected_language);

    $scope.changeLanguage = function (langKey) {
        $cookies.put('lang',langKey);
        $translate.use(langKey);
    };
}]);

function translateConfig($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: '/assets/resources/',
        suffix: '.json'
    });

    $translateProvider.useLoaderCache(true);
    $translateProvider.forceAsyncReload(true);

    $translateProvider.registerAvailableLanguageKeys(['en', 'fr'], {
        'en_*': 'en',
        'fr_*': 'fr'
    });

    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');
    $translateProvider.useSanitizeValueStrategy('escape');
}
})();
