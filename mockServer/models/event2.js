var evento = {
      "id": "2",
      "title": "almuerzo",
      "description":"Descripcion evento 2",
	"users":["1","2","3"]      
    };

var Event = function(evento) {

  this.name = evento.name;

};

Event.findAll = function() {
  return evento;
}

exports.Event = Event;
