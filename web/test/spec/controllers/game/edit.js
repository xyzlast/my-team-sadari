'use strict';

describe('Controller: GameEditCtrl', function () {

  // load the controller's module
  beforeEach(module('sadariInfoApp'));

  var GameEditCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    GameEditCtrl = $controller('GameEditCtrl', {
      $scope: scope
    });
  }));
});
