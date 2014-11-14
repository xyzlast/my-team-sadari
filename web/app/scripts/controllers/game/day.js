'use strict';

/**
 * @ngdoc function
 * @name sadariInfoApp.controller:GameDayCtrl
 * @description
 * # GameDayCtrl
 * Controller of the sadariInfoApp
 */
angular.module('sadariInfoApp').controller('GameDayCtrl', function ($scope, $routeParams, $location, GameService) {
  $scope.date = null;
  $scope.games = [];

  var init = function() {
    var year = $routeParams.year;
    var month = $routeParams.month;
    var day = $routeParams.day;
    $scope.date = year + '-' + month + '-' + day;
    return GameService.getGamesByDay(year, month, day, listup);
  };

  var listup = function(data) {
    if(data.length == 0) {
      $scope.games = [];
    } else {
      $scope.games = data;
    }
  };

  $scope.addNewGame = function() {
    $location.path('game/add');
  };

  $scope.edit = function(id) {
    var params = {
      id: id
    };
    $location.path('game/edit').search(params);
  };

  init();
});
