'use strict';

describe('Service: CalendarService', function () {

  // load the service's module
  beforeEach(module('sadariInfoApp'));

  // instantiate service
  var CalendarService, httpBackend;
  beforeEach(inject(function (_CalendarService_, $httpBackend) {
    CalendarService = _CalendarService_;
    httpBackend = $httpBackend;
  }));

  it('calendarService init', function () {
    expect(!!CalendarService).toBe(true);
  });

});
