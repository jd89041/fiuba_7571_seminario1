package soporte.reglas.respuesta

import soporte.reglas.Regla

abstract class ReglaRespuesta extends Regla {

    @Override
    def obtenerCategoría() {
        "Respuesta"
    }

    def obtenerMensajesDeTemas(mensajes, temas) {
        def resultado = []
        mensajes.each { mensaje ->
            temas.each { tema ->
                if (mensaje.perteneceAlTema(tema))
                    resultado.add(mensaje)
            }
        }
        resultado
    }

    abstract def procesar(pedidoSoporte, mensajes, temasDeRespuesta)

    def aplicar(pedidoSoporte, mensajes, temasDeRespuesta) {
        def mensajesDePedido = mensajes.findAll {
            !it.esRespuesta
        }
        procesar(pedidoSoporte, mensajesDePedido, temasDeRespuesta)
    }

}
