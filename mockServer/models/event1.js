var evento = {
      "id": "1",
      "title": "Desayuno",
      "description":"Descripcion evento 1",
	"users":["1","2"]      
    };

var Event = function(evento) {

  this.name = evento.name;

};

Event.findAll = function() {
  return evento;
}

exports.Event = Event;
