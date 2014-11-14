'use strict';

/**
 * @ngdoc function
 * @name sadariInfoApp.controller:GameEditCtrl
 * @description
 * # GameEditCtrl
 * Controller of the sadariInfoApp
 */
angular.module('sadariInfoApp').controller('GameEditCtrl', function ($scope, $routeParams, GameService, $location) {
  $scope.data = null;
  $scope.date = null;
  $scope.numberOptions = {
    format: 'n0'
  };
  var init = function() {
    return GameService.read($routeParams.id, buildData);
  };

  var buildData = function(data) {
    $scope.data = data;
    if($scope.data.id) {
      $scope.date = kendo.toString(new Date(data.date), 'yyyy-MM-dd');
    } else {
      var year = parseInt($routeParams.year);
      var month = parseInt($routeParams.month) - 1;
      var day = parseInt($routeParams.day);
      $scope.date = kendo.toString(new Date(year, month, day), 'yyyy-MM-dd');
    }
  };

  $scope.save = function() {
    var data = {
      date: $scope.date,
      matchingNumber : $scope.data.matchingNumber,
      cost: $scope.data.cost,
      description: $scope.data.description,
      playerResults: []
    };
    angular.forEach($scope.data.players, function(player) {
      if(player.joined) {
        var p = {
          playerId: player.player.id,
          ownNumber: player.ownNumber
        };
        data.playerResults.push(p);
      }
    });
    var result;
    if($scope.data.id) {
      result = GameService.edit($scope.data.id, data, goUpdateCompleted);
    } else {
      result = GameService.add(data, goUpdateCompleted);
    }
    if(!result.ok) {
      $scope.showGlobalMessage('danger', result.message);
    }
    return result;
  };

  $scope.remove = function() {
    GameService.remove($scope.data.id, function(jsonResult) {
      console.log(jsonResult);
      $scope.showGlobalMessage('success', "삭제되었습니다.");
      $location.path("");
    });
  };

  var goUpdateCompleted = function(jsonResult) {
    if(jsonResult.ok) {
      $scope.showGlobalMessage('success', "저장되었습니다.");
      $location.path("");
    } else {
      $scope.showGlobalMessage('warning', jsonResult.message);
    }
  };
  init();
});
