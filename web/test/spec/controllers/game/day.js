'use strict';

describe('Controller: GameDayCtrl', function () {

  // load the controller's module
  beforeEach(module('sadariInfoApp'));

  var GameDayCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    GameDayCtrl = $controller('GameDayCtrl', {
      $scope: scope
    });
  }));
});
