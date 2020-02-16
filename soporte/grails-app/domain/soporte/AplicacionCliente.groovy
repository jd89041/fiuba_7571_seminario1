package soporte

import soporte.notificaciones.aplicacion.*
import soporte.reglas.*
import soporte.reglas.etiquetado.*
import soporte.reglas.asignacion.*
import soporte.reglas.ordenamiento.*
import soporte.reglas.respuesta.*

class AplicacionCliente {
    String nombre

    boolean autoEtiquetar
    boolean autoResponder
    boolean autoAsignar

    static belongsTo = [organizacion: Organizacion]

    static hasMany = [miembros: MiembroEquipo, pedidosSoporte: PedidoSoporte, temas: Tema, reglas: Regla]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
    }

    static constraints = {
        nombre blank: false
    }

    def agregarMiembroConEmail(emailNuevoMiembro) {
        def miembro = organizacion.obtenerMiembro(emailNuevoMiembro)
        miembro.notificar(new NotificacionAltaDeMiembroEnAplicacion(nombre))
        addToMiembros(miembro)
        save(failOnError: true)
    }

    def removerMiembroConEmail(email) {
        // remover todas las tareas que tenia asignadas relacionadas con esta app
        def miembro = miembros.find {
            it.email = email
        }
        miembro.notificar(new NotificacionBajaDeMiembroDeAplicacion(nombre))
        removeFromMiembros(miembro)
        save(failOnError: true)
    }

    def agregarTema(tema) {
        addToTemas(tema)
        // informar para que se puedan recalcular las etiquetas
    }

    def removerTema(tema) {
        removeFromTemas(tema)
    }

    def recuperarPedidoSoporte(email) {
        def pedido
        pedidosSoporte.each {
            if (it.emailAutor.equals(email))
                pedido = it
        }
        return pedido
    }

    // pedido de soporte recibido tiene:
    // - id del usuario con el que se trackea la historia
    // - mensaje nuevo
    def gestionarPedidoSoporteEntrante(mensajeSoporteEntrante) {
        String emailAutor = mensajeSoporteEntrante.emailAutor
        def pedidoSoporte = recuperarPedidoSoporte(emailAutor)
        if (!pedidoSoporte) {
            AutorPedidoSoporte autor = pedidosSoporte.find { it.emailAutor == emailAutor }
            if (!autor) {
                autor = new AutorPedidoSoporte(emailAutor)
                autor.nombre = mensajeSoporteEntrante.nombreAutor
                autor.aplicacion = mensajeSoporteEntrante.aplicacion
                // agregar otras cosas como la plataforma y el identificador de device para enviar luego al autor
                // notificaciones!!
            }
            pedidoSoporte = new PedidoSoporte(autor)
            addToPedidosSoporte(pedidoSoporte)
            autor.save(failOnError: true)
        }
        pedidoSoporte.agregarMensaje(mensajeSoporteEntrante, false)
        if (autoEtiquetar)
            pedidoSoporte.etiquetar(reglas.findAll { it.instanceOf(ReglaEtiquetado) })
        // if auto responder y regla auto respuesta
        def respuesta
        if (autoResponder)
            respuesta = pedidoSoporte.responder(reglas.findAll { it.instanceOf(ReglaRespuesta) })
        if (!respuesta && !pedidoSoporte.estaAsignado()) {
            if (autoAsignar)
                pedidoSoporte.asignarConReglas(reglas.findAll {it.instanceOf(ReglaAsignacion)}, reglas.find { it.instanceOf(ReglaOrdenamiento) })
        }
        pedidoSoporte.save(failOnError: true)
        this.save(failOnError: true)
    }

    def agregarRegla(regla) {
        addToReglas(regla)
        regla.save(failOnError: true)
    }

    def obtenerRegla(nombreRegla) {
        reglas.find {
            it.obtenerNombre() == nombreRegla
        }
    }

    def obtenerConfigGeneral() {
        [
            [nombre: "Auto Etiquetar", id: "autoEtiquetar", activa: autoEtiquetar],
            [nombre: "Auto Responder", id: "autoResponder", activa: autoResponder],
            [nombre: "Auto Asignar", id: "autoAsignar", activa: autoAsignar]
        ]
    }

    def actualizarConfigGeneral(config) {
        setAutoEtiquetar(config.autoEtiquetar)
        setAutoResponder(config.autoResponder)
        setAutoAsignar(config.autoAsignar)
        save(failOnError: true)
    }
}
