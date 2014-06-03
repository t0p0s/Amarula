var event2 = require('../models/event2').Event;

exports.get = function(req, res){
  res.json(event2.findAll());
}
