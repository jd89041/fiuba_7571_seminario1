package soporte

import groovy.transform.Sortable
import org.joda.time.Instant

@Sortable(includes = ['fecha'])
class MensajePedidoSoporte {
    String autor
    String mensaje
    long fecha
    boolean esRespuesta

    static belongsTo = [pedidoSoporte: PedidoSoporte]

    static constraints = {
        pedidoSoporte nullable: false
    }

    def MensajePedidoSoporte(nombreAutor, mensaje, esRespuesta) {
        setAutor(nombreAutor)
        setMensaje(mensaje)
        setFecha(new Instant().getMillis())
        setEsRespuesta(esRespuesta)
    }

    def perteneceAlTema(tema) {
        tema.obtenerOcurrencias(mensaje) > 0
    }

    def obtenerOcurrenciasDeTemas(temas) {
        def ocurrencias = [:]
        temas.each {
            def ocurrenciasTema = it.obtenerOcurrencias(mensaje)
            if (ocurrenciasTema > 0)
                ocurrencias[it.nombre] = ocurrenciasTema
        }
        ocurrencias
    }
}
