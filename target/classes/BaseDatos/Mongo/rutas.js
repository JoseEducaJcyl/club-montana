// Datos de ejemplo para MongoDB. Ejecutar con:  mongosh mongo/rutas.js
// Cada documento es la ficha de una ruta de MySQL (ruta_id = ruta.id).

use("club_montana");

db.rutas.deleteMany({});

db.rutas.insertMany([
  { ruta_id: 1, nombre: "Subida al Monte Santa Trega", dificultad: "media", guia: "Marta Souto Fernández" },
  { ruta_id: 2, nombre: "Camino da Costa",             dificultad: "baja",  guia: "Lucía Alonso Rey" },
  { ruta_id: 3, nombre: "Ruta dos Muíños do Folón",    dificultad: "baja",  guia: "Xoán Pérez Lago" },
  { ruta_id: 4, nombre: "Cabo Silleiro",               dificultad: "media", guia: "Brais Domínguez Vila" },
  { ruta_id: 5, nombre: "Serra da Groba",              dificultad: "alta",  guia: "Marta Souto Fernández" },
  { ruta_id: 6, nombre: "Pena Corneira",               dificultad: "alta",  guia: "Iago Rodríguez Sousa" }
]);

//db.rutas.createIndex({ ruta_id: 1 }, { unique: true });
