package soporte

import org.joda.time.Instant

class MensajePedidoSoporte {
    String autor
    String mensaje
    long fecha

    static belongsTo = [pedidoSoporte: PedidoSoporte]

    static constraints = {
        pedidoSoporte nullable: false
    }

    def MensajePedidoSoporte(autor, mensaje) {
        this.autor = autor
        this.mensaje = mensaje
        fecha = new Instant().getMillis()
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
