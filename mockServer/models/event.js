var dummy = {
  "events": [
    {
      "id": "1",
      "title": "Desayuno",
      "description":"Descripcion evento 1"
    },
    {
      "id": "2",
      "title": "Almuerzo",
      "description":"Descripcion evento 2"
    },
    {
      "id": "3",
      "title": "Merienda",
      "description":"Descripcion evento 3"
    },
    {
      "id":"4",
      "title": "Cena",
      "description":"Descripcion evento 4"
    }
  ]
};

var Event = function(evento) {

  this.name = evento.name;

};

Event.findAll = function() {
  return dummy;
}

exports.Event = Event;
