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
            pedidoSoporte.etiquetar(reglas.findAll { it.instanceOf(ReglaEtiquetado) && activa })
        // if auto responder y regla auto respuesta
        def respuesta
        if (autoResponder)
            respuesta = pedidoSoporte.responder(reglas.findAll { it.instanceOf(ReglaRespuesta) && it.activa })
        if (respuesta) {
            // generar respuesta y sumarla a la charla!
            pedidoSoporte.agregarMensaje([nombreAutor: "Automatizado", contenido: respuesta], true)
        } else {
            if (!pedidoSoporte.estaAsignado()) {
                if (autoAsignar)
                    pedidoSoporte.asignarConReglas(reglas.findAll {
                        it.instanceOf(ReglaAsignacion) && it.activa
                    }, reglas.find { it.instanceOf(ReglaOrdenamiento) })
            }
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

    def obtenerTemas() {
        temas
    }

    def actualizarConfigGeneral(config) {
        setAutoEtiquetar(config.autoEtiquetar)
        setAutoResponder(config.autoResponder)
        setAutoAsignar(config.autoAsignar)
        save(failOnError: true)
    }

    def obtenerConversacionPedido(idPedido) {
        def pedido = pedidosSoporte.find {
            it.id == idPedido
        }
        pedido.obtenerConversacion()
    }

    def obtenerPedidosSoporteMiembro(miembro) {
        pedidosSoporte.findAll {
            miembro.estaTrabajandoEnPedidoSoporte(it)
        }
    }

    def obtenerPedidoSoporte(idPedido) {
        pedidosSoporte.find {
            it.id == idPedido
        }
    }

    def asignarPedidoSoporte(idPedidoSoporte, miembro) {
        PedidoSoporte pedido = obtenerPedidoSoporte(idPedidoSoporte)
        pedido.asignar(miembro)
    }

    def agregarTema(nombreTema) {
        if (temas.any {
            it.nombre == nombreTema
        })
            false
        else {
            Tema nuevoTema = new Tema()
            nuevoTema.nombre = nombreTema
            addToTemas(nuevoTema)
            //save(failOnError: true)
            true
        }
    }

    def borrarTema(nombreTema) {
        Tema tema = temas.find {
            it.nombre == nombreTema
        }
        if (tema) {
            removeFromTemas(tema)
            tema.delete()
            //save(failOnError: true)
        }
    }

    def agregarRespuestaAutomatica(nombreTema, tituloRespuesta) {
        Tema tema = temas.find {
            it.nombre == nombreTema
        }
        if (tema)
            tema.agregarRespuestaAutomatica(tituloRespuesta)
        else
            false
    }

    def borrarRespuestaAutomatica(nombreTema, tituloRespuesta) {
        Tema tema = temas.find {
            it.nombre == nombreTema
        }
        if (tema)
            tema.borrarRespuestaAutomatica(tituloRespuesta)
    }

    def actualizarRespuestaAutomatica(nombreTema, tituloRespuesta, mensaje, palabrasClave) {
        Tema tema = temas.find {
            it.nombre == nombreTema
        }
        if (tema)
            tema.actualizarRespuestaAutomatica(tituloRespuesta, mensaje, palabrasClave)
        else
            false
    }

    def actualizarPalabrasClave(nombreTema, palabrasClave) {
        Tema tema = temas.find {
            it.nombre == nombreTema
        }
        if (tema)
            tema.actualizarPalabrasClave(palabrasClave)
        else
            false
    }
}
