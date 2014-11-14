'use strict';

/**
 * @ngdoc service
 * @name sadariInfoApp.GameService
 * @description
 * # GameService
 * Service in the sadariInfoApp.
 */
angular.module('sadariInfoApp').service('GameService', function GameService(Restangular, GameDataValidator) {
  var self = this;
  self.getCalendar = function(year, month, func) {
    var params = {
      year: year,
      month: month
    }
    Restangular.all('game').get('calendar', params).then(function(jsonResult) {
      if(jsonResult.ok && func) {
        func(jsonResult.data);
      }
    });
    return true;
  };

  self.getGamesByDay = function(year, month, day, func) {
    var params = {
      year: year,
      month: month,
      day: day
    };
    Restangular.all('game').get('day', params).then(function(jsonResult) {
      if(jsonResult.ok && func) {
        func(jsonResult.data);
      }
    });
    return true;
  };

  self.read = function(id, func) {
    if(id) {
      Restangular.all('game/edit').get(id).then(function(jsonResult) {
        if(jsonResult.ok && func) {
          func(jsonResult.data);
        }
      });
      return true;
    } else {
      Restangular.all('game').get('add').then(function(jsonResult) {
        if(jsonResult.ok && func) {
          func(jsonResult.data);
        }
      });
    }
  };

  self.add = function(data, func) {
    var validateResult = GameDataValidator.validate(data);
    if(!validateResult.ok) {
      return validateResult;
    }
    Restangular.all('game/add').post(data).then(function(jsonResult) {
      if(func) {
        func(jsonResult);
      }
    });
    return validateResult;
  };

  self.edit = function(id, data, func) {
    var validateResult = GameDataValidator.validate(data);
    if(!validateResult.ok) {
      return validateResult;
    }
    Restangular.all('game/edit/' + id).post(data).then(function(jsonResult) {
      if(func) {
        func(jsonResult);
      }
    });
    return validateResult;
  };

  self.remove = function(id, func) {
    Restangular.all('game/remove/' + id).remove().then(function(jsonResult) {
      if(func) {
        func(jsonResult);
      }
    });
  };
});
