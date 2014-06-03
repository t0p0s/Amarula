var event1 = require('../models/event1').Event;

exports.get = function(req, res){
  res.json(event1.findAll());
}
