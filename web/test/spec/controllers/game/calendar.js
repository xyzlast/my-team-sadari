'use strict';

describe('Controller: GameCalendarCtrl', function () {

  // load the controller's module
  beforeEach(module('sadariInfoApp'));
  var GameCalendarCtrl, scope, httpBackend;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $httpBackend) {
    scope = $rootScope.$new();
    httpBackend = $httpBackend;

    GameCalendarCtrl = $controller('GameCalendarCtrl', {
      $scope: scope
    });
  }));
});
