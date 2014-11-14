'use strict';

describe('Service: GameService', function () {

  // load the service's module
  beforeEach(module('sadariInfoApp'));

  // instantiate service
  var GameService, httpBackend;
  beforeEach(inject(function (_GameService_, $httpBackend) {
    GameService = _GameService_;
    httpBackend = $httpBackend;
  }));

  it('GameService init', function () {
    expect(!!GameService).toBe(true);
  });

  it('GameService.getCalendar', function() {
    var response = {"ok":true,"message":null,"data":{"year":2014,"month":11,"dayGames":[{"id":1,"date":1415286000000,"playerNames":"서인석(1),윤영권(2),서강춘(3),김지욱(4),강민석(5),주진영(6),김희범(7)","matchPlayerName":"주진영","matchNumber":"6","valid":true},{"id":2,"date":1415545200000,"playerNames":"서인석(1),윤영권(2),서강춘(3),김지욱(4),강민석(5),주진영(6),김희범(7)","matchPlayerName":"김지욱","matchNumber":"4","valid":true},{"id":3,"date":1415545200000,"playerNames":"서인석(1),윤영권(2),서강춘(3),김지욱(4),강민석(5),주진영(6),김희범(7)","matchPlayerName":"주진영","matchNumber":"6","valid":true},{"id":4,"date":1415687482000,"playerNames":"강민석(1),김지욱(2),김희범(3),서강춘(4),서인석(5),윤영권(6),주진영(7)","matchPlayerName":"서인석","matchNumber":"5","valid":true}]}};
    httpBackend.expectGET('/sadari-api/game/calendar?month=11&year=2014').respond(200, response);
    expect(GameService.getCalendar(2014, 11)).toBe(true);
    httpBackend.flush();
  });

  it('GameService.getByDay', function() {
    var response = {"ok":true,"message":null,"data":[{"id":2,"deleted":false,"date":1415545200000,"results":[{"id":8,"deleted":false,"player":{"id":1,"deleted":false,"name":"서인석","defaultAmount":2,"matchCount":0,"amount":2},"ownNumber":"1"},{"id":9,"deleted":false,"player":{"id":2,"deleted":false,"name":"윤영권","defaultAmount":4,"matchCount":0,"amount":4},"ownNumber":"2"},{"id":10,"deleted":false,"player":{"id":3,"deleted":false,"name":"서강춘","defaultAmount":4,"matchCount":0,"amount":4},"ownNumber":"3"},{"id":11,"deleted":false,"player":{"id":4,"deleted":false,"name":"김지욱","defaultAmount":5,"matchCount":0,"amount":5},"ownNumber":"4"},{"id":12,"deleted":false,"player":{"id":5,"deleted":false,"name":"강민석","defaultAmount":6,"matchCount":0,"amount":6},"ownNumber":"5"},{"id":13,"deleted":false,"player":{"id":6,"deleted":false,"name":"주진영","defaultAmount":9,"matchCount":0,"amount":9},"ownNumber":"6"},{"id":14,"deleted":false,"player":{"id":7,"deleted":false,"name":"김희범","defaultAmount":11,"matchCount":0,"amount":11},"ownNumber":"7"}],"matchingNumber":"4","cost":48000,"description":"","matchingPlayer":{"id":4,"deleted":false,"name":"김지욱","defaultAmount":5,"matchCount":0,"amount":5}},{"id":3,"deleted":false,"date":1415545200000,"results":[{"id":15,"deleted":false,"player":{"id":1,"deleted":false,"name":"서인석","defaultAmount":2,"matchCount":0,"amount":2},"ownNumber":"1"},{"id":16,"deleted":false,"player":{"id":2,"deleted":false,"name":"윤영권","defaultAmount":4,"matchCount":0,"amount":4},"ownNumber":"2"},{"id":17,"deleted":false,"player":{"id":3,"deleted":false,"name":"서강춘","defaultAmount":4,"matchCount":0,"amount":4},"ownNumber":"3"},{"id":18,"deleted":false,"player":{"id":4,"deleted":false,"name":"김지욱","defaultAmount":5,"matchCount":0,"amount":5},"ownNumber":"4"},{"id":19,"deleted":false,"player":{"id":5,"deleted":false,"name":"강민석","defaultAmount":6,"matchCount":0,"amount":6},"ownNumber":"5"},{"id":20,"deleted":false,"player":{"id":6,"deleted":false,"name":"주진영","defaultAmount":9,"matchCount":0,"amount":9},"ownNumber":"6"},{"id":21,"deleted":false,"player":{"id":7,"deleted":false,"name":"김희범","defaultAmount":11,"matchCount":0,"amount":11},"ownNumber":"7"}],"matchingNumber":"6","cost":6000,"description":"","matchingPlayer":{"id":6,"deleted":false,"name":"주진영","defaultAmount":9,"matchCount":0,"amount":9}}]};
    httpBackend.expectGET('/sadari-api/game/day?day=11&month=11&year=2014').respond(200, response);
    expect(GameService.getGamesByDay(2014, 11, 11)).toBe(true);
    httpBackend.flush();
  });

  it('GameService.read', function() {
    var response = {"ok":true,"message":null,"data":{"matchingNumber":"5","cost":5900,"description":"주진영책임 걸리다.","id":4,"players":[{"player":{"id":5,"deleted":false,"name":"강민석","defaultAmount":6,"matchCount":0,"amount":6},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":4,"deleted":false,"name":"김지욱","defaultAmount":5,"matchCount":0,"amount":5},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":7,"deleted":false,"name":"김희범","defaultAmount":11,"matchCount":0,"amount":11},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":3,"deleted":false,"name":"서강춘","defaultAmount":4,"matchCount":0,"amount":4},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":1,"deleted":false,"name":"서인석","defaultAmount":2,"matchCount":0,"amount":2},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":2,"deleted":false,"name":"윤영권","defaultAmount":4,"matchCount":0,"amount":4},"joined":false,"matched":false,"ownNumber":null},{"player":{"id":6,"deleted":false,"name":"주진영","defaultAmount":9,"matchCount":0,"amount":9},"joined":false,"matched":false,"ownNumber":null}]}};
    httpBackend.expectGET('/sadari-api/game/edit/3').respond(200, response);
    expect(GameService.read(3)).toBe(true);
    httpBackend.flush();
  });

  it('GameService.edit', function() {
    var data = {
      date: '2014-11-10',
      description: '가나다라',
      cost: '300000',
      matchingNumber: '3',
      playerResults: []
    };
    data.playerResults.push({playerId: 1, ownNumber: '1'});
    data.playerResults.push({playerId: 2, ownNumber: '2'});
    data.playerResults.push({playerId: 4, ownNumber: '4'});
    data.playerResults.push({playerId: 5, ownNumber: '5'});
    expect(GameService.edit(3, data).ok).toBe(false);
    httpBackend.expectPOST('/sadari-api/game/edit/3').respond(200, {});
    data.playerResults.push({playerId: 3, ownNumber: '3'});
    expect(GameService.edit(3, data).ok).toBe(true);
    httpBackend.flush();
  });
});
