'use strict';

/**
 * @ngdoc overview
 * @name sadariInfoApp
 * @description
 * # sadariInfoApp
 *
 * Main module of the application.
 */
angular
  .module('sadariInfoApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'restangular',
    'kendo.directives'
  ])
  .config(function($routeProvider, RestangularProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/game/calendar.html',
        controller: 'GameCalendarCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/calendar', {
        templateUrl: 'views/calendar.html',
        controller: 'CalendarCtrl'
      })
      .when('/game/calendar', {
        templateUrl: 'views/game/calendar.html',
        controller: 'GameCalendarCtrl'
      })
      .when('/game/day', {
        templateUrl: 'views/game/day.html',
        controller: 'GameDayCtrl'
      })
      .when('/game/add', {
        templateUrl: 'views/game/edit.html',
        controller: 'GameEditCtrl'
      })
      .when('/game/edit', {
        templateUrl: 'views/game/edit.html',
        controller: 'GameEditCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
    RestangularProvider.setDefaultHttpFields({
      cache: false
    });
    RestangularProvider.setBaseUrl('/sadari-api');
    kendo.culture('ko-KR');
  });
