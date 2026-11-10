# Club de Montaña

Proyecto JavaFX con MySQL y MongoDB.

- **Formulario 1 · Rutas.** Alta, modificación y baja de rutas. Insertar, modificar y eliminar se hacen en MySQL (tabla `ruta`) y en MongoDB (colección `rutas`). El botón **Participantes** abre el formulario 2 con la ruta seleccionada.
- **Formulario 2 · Participantes.** Lista los socios que han hecho la ruta y permite quitar a uno de ellos. Solo usa MySQL (tabla `ruta_socio`).

## Modelo de datos

MySQL (`src/main/resources/Base de datos/mongo/sql`):

| Tabla | Campos |
|---|---|
| `socio` | `id`, `nombre`, `telefono`, `fecha_alta` |
| `ruta` | `id`, `nombre`, `dificultad`, `guia_id` (FK → `socio`) |
| `ruta_socio` | `ruta_id` (FK → `ruta`, ON DELETE CASCADE), `socio_id` (FK → `socio`) |

MongoDB, colección `rutas` (`src/main/resources/Base de datos/mongo`):

```json
{ "ruta_id": 1, "nombre": "Subida al Monte Santa Trega", "dificultad": "media",
  "guia": "Marta Souto Fernández" }
```

## Puesta en marcha

Requisitos: JDK 21, Maven 3.9+, MySQL 8 (o MariaDB) y MongoDB en local.

1. Crear la base de datos de MySQL con datos de ejemplo:
   `mysql -u root -p < sql/club.sql`
2. Cargar las fichas de las rutas en MongoDB:
   `mongosh mongo/rutas.js`
3. Revisar usuario y contraseña en `src/main/resources/db.properties`.
4. Ejecutar:
   `mvn javafx:run`

## Estructura

```
src/main/java/club
├── App.java                      arranque y cambio de pantalla
├── model/                        Ruta, Socio
├── db/Conexion.java              conexiones a MySQL y MongoDB
├── dao/                          RutaDao, SocioDao, ParticipanteDao (MySQL) y RutaMongoDao (Mongo)
├── service/RutaServicio.java     mantiene sincronizadas MySQL y Mongo
├── controller/                   RutasController, ParticipantesController
└── util/Avisos.java              alertas
src/main/resources
├── fxml/                         rutas.fxml, participantes.fxml
├── css/estilo.css
└── db.properties
```

## Puntos a tener en cuenta

- MySQL y MongoDB no comparten transacción. `RutaServicio` inserta primero en MySQL y, si Mongo falla, deshace el insert. En modificar y eliminar no hay compensación: si falla la segunda base de datos, quedan desincronizadas.
- Las operaciones con la base de datos se ejecutan en el hilo de JavaFX para que el código sea sencillo. En una aplicación real irían en un `Task`.
