package soporte.reglas.respuesta

import org.joda.time.DateTime

class SoloTemas extends ReglaRespuesta {

    List<String> temas

    static constraints = {
        temas nullable: false
    }

    @Override
    def obtenerNombre() {
        "Solo temas"
    }

    @Override
    def obtenerDescripcion() {
        "Define los temas acerca de los cuales se puede responder automáticamente"
    }

    @Override
    def procesar(pedidoSoporte, mensajes, temasDeRespuesta) {
        def nuevosMensajes = obtenerMensajesDeTemas(mensajes, temasDeRespuesta)
        def nuevosTemasRespuesta = temasDeRespuesta.findAll {
            it.nombre in temas
        }
        [nuevosMensajes, nuevosTemasRespuesta]
    }
}
