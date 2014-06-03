
/**
 * Module dependencies.
 */

var express = require('express');
var http = require('http');
var events = require('./routes/events');
var event1 = require('./routes/event1');
var event2 = require('./routes/event2');

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);

// routes
app.get('/api/events', events.get);
app.get('/api/events/1',event1.get);
app.get('/api/events/2',event2.get);

// http setup
http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});

// creation
exports.app = app;
