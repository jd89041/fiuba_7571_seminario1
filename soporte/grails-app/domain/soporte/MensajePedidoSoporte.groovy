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
}
