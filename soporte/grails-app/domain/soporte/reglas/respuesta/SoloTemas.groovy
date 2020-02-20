package soporte.reglas.respuesta

import org.joda.time.DateTime

class SoloTemas extends ReglaRespuesta {

    static final String = "Solo temas"

    List<String> temas

    static constraints = {
        temas nullable: false
    }

    @Override
    def obtenerTipo() {
        "lista_alfabetica"
    }

    @Override
    def obtenerNombre() {
        NOMBRE
    }

    @Override
    def obtenerDescripcion() {
        "Define los temas acerca de los cuales se puede responder automáticamente"
    }

    @Override
    def aplicar(pedidoSoporte, temasDeRespuesta) {
        def nuevosTemasRespuesta = temasDeRespuesta.findAll {
            it.nombre in temas
        }
        nuevosTemasRespuesta
    }
}
