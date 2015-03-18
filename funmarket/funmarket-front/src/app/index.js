'use strict';

angular.module('front', ['ngResource', 'ngRoute', 'angularFileUpload', 'pascalprecht.translate'])
  .value('funmarketApiUrl', 'http://funmarket-api.herokuapp.com')
  .constant('availableLanguages', ['fi', 'en'])
  //.value('funmarketApiUrl', 'http://localhost:8080')
  .config(function ($routeProvider, $translateProvider, availableLanguages) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .when('/create', {
        templateUrl: 'app/create/create.html',
        controller: 'CreateAdCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

    $translateProvider.useStaticFilesLoader({
        prefix: 'locale/locale-',
        suffix: '.json'
    });
    $translateProvider.preferredLanguage(availableLanguages[0]);
  })
  .controller('localeController', function($scope, $translate, availableLanguages){

        $scope.availableLanguages = availableLanguages;
        $scope.currentLanguage = $translate.proposedLanguage();

        $scope.changeLang = function(lang) {
            $translate.use(lang);
            $scope.currentLanguage = lang;
        };
  });