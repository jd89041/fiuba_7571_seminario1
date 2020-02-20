package soporte

import soporte.notificaciones.pedidos_soporte.NotificacionNuevoMensajeEnPedidoSoporte
import soporte.reglas.ordenamiento.MenosPedidosAplicacion

class PedidoSoporte {

    String emailAutor
    List<String> etiquetas
    Map<String, Integer> ocurrenciasDeTemas

    static hasOne = [miembro: MiembroEquipo, autor: AutorPedidoSoporte]
    static hasMany = [mensajes: MensajePedidoSoporte]

    static belongsTo = [aplicacion: AplicacionCliente]

    static constraints = {
        miembro nullable: true
    }

    def PedidoSoporte(autor) {
        setAutor(autor)
        emailAutor = autor.email
        etiquetas = []
        ocurrenciasDeTemas = [:]
    }

    def agregarMensaje(pedidoSoporteEntrante, esRespuesta) {
        // evaluar y luego se puede taggear
        def nuevoMensaje = new MensajePedidoSoporte(pedidoSoporteEntrante.nombreAutor, pedidoSoporteEntrante.contenido, esRespuesta)
        procesarMensaje(nuevoMensaje)
        addToMensajes(nuevoMensaje)
        if (estaAsignado() && !esRespuesta)
            miembro.notificar(new NotificacionNuevoMensajeEnPedidoSoporte(aplicacion.nombre))
    }

    // evalua el mensaje en base a los temas de la aplicacion cliente y carga una lista de ponderaciones
    def procesarMensaje(mensaje) {
        // if nuevos temas procesar todos!
        def ocurrenciasTemas = mensaje.obtenerOcurrenciasDeTemas(aplicacion.temas)
        ocurrenciasTemas.each { tema, ocurrencias ->
            if (!ocurrenciasDeTemas[tema])
                ocurrenciasDeTemas[tema] = 0
            ocurrenciasDeTemas[tema] += ocurrencias
        }
    }

    def etiquetar(reglas) {
        // etiqueta / ocurrencias
        // 1) identificar si el mensaje es de un agente o de un usuario (filtro solo mensajes de usuario)
        if (reglas.size() > 0) {
            ocurrenciasDeTemas.each { ocurrencia ->
                if (reglas.every { regla -> regla.cumple(ocurrencia.key, ocurrencia.value) } && !(ocurrencia.key in etiquetas)) {
                    addToEtiquetas(ocurrencia.key)
                }
            }
        }
    }

    def responder(reglas) {
        def temasRespuesta = []
        if (reglas.size() > 0) {
            if (aplicacion.temas.size() > 0) {
                temasRespuesta = aplicacion.temas.collect()  // copia
                def mensajes = this.mensajes.collect()
                reglas.each {
                    if (mensajes.size() > 0 && temasRespuesta.size() > 0)
                        (mensajes, temasRespuesta) = it.aplicar(this, mensajes, temasRespuesta)
                }
            }
        }
        if (temasRespuesta.size() > 0)
            temasRespuesta[0].obtenerRespuestaAutomatica(mensajes.last())
            //temasRespuesta.preguntasFrecuentes[0][0].respuesta // procesar que no se devuelva repetido
        else
            null
    }

    def estaAsignado() {
        miembro != null
    }

    def asignarConReglas(reglasAsignacion, reglaOrdenamiento) {
        def miembrosAsignables = aplicacion.miembros.collect()
        if (reglasAsignacion.size() > 0) {
            reglasAsignacion.each { regla ->
                if (miembrosAsignables.size() > 0)
                    miembrosAsignables = regla.aplicar(this, miembrosAsignables)
            }
        }

        if (miembrosAsignables.size() > 0) {
            def ordenados = new MenosPedidosAplicacion().aplicar(this, miembrosAsignables)
            def miembro = ordenados[0]
            asignar(miembro)
            true
        } else
            false
    }

    def asignar(miembro) {
        if (this.miembro)
            this.miembro.removerPedidoSoporte(this)
        setMiembro(miembro)
        this.miembro.agregarPedidoSoporte(this)
    }

    def obtenerConversacion() {
        mensajes.sort()
    }
}
