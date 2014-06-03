var eventModel = require('../models/event').Event;

exports.get = function(req, res){
  res.json(eventModel.findAll());
}
