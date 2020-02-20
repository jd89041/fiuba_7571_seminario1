package soporte

import soporte.notificaciones.NotificacionBienvenida
import soporte.notificaciones.aplicacion.NotificacionAltaAplicacion
import soporte.notificaciones.plan.NotificacionActualizacionPlan

class Organizacion {

    String nombre

    static hasOne = [plan: Plan]
    static hasMany = [miembros: MiembroEquipo, aplicaciones: AplicacionCliente]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
        secondary cascade: 'all-delete-orphan'
    }

    static constraints = {
        nombre blank: false
    }

    def Organizacion(String nombre) {
        setNombre(nombre)
        setPlan(new Plan())
    }

    def obtenerAplicacion(nombreAplicacion) {
        aplicaciones.find {
            it.nombre == nombreAplicacion
        }
    }

    def obtenerMiembro(email) {
        miembros.find {
            it.email == email
        }
    }

    def obtenerPlanesDisponibles() {
        if (plan.asignado())
            PlanOferta.list().findAll() {
                it.puedeAdquirirse(plan.nombre, aplicaciones.size(), miembros.size())
            }
        else
            PlanOferta.list()
    }

    def puedeInvitarMiembros() {
        plan.puedeAgregarMiembros(miembros.size())
    }

    def puedeAgregarAplicacionesCliente() {
        plan.puedeAgregarAplicaciones(aplicaciones.size())
    }

    def tieneMiembro(miembro) {
        miembro && miembros.any {
            it.email == miembro.email
        }
    }

    def soportaPlanOferta(PlanOferta planOferta) {
        planOferta.puedeAdquirirse(aplicaciones.size(), miembros.size())
    }

    def adquirirPlan(PlanOferta planOferta) {
        plan.activar(planOferta)
        save(failOnError: true)
        miembros.each {
            it.notificar(new NotificacionActualizacionPlan(planOferta.nombre))
        }
    }

    def agregarAplicacion(AplicacionCliente aplicacion) {
        addToAplicaciones(aplicacion)
        save(failOnError: true)
        miembros.each {
            it.notificar(new NotificacionAltaAplicacion(aplicacion.nombre))
        }
    }

    def agregarMiembro(MiembroEquipo miembro) {
        addToMiembros(miembro)
        save(failOnError: true)
        miembro.notificar(new NotificacionBienvenida(nombre))
    }

    def removerMiembroConEmail(email) {
        def miembro = miembros.find {
            it.email == email
        }
        removeFromMiembros(miembro)
        miembro.delete()
    }

    def obtenerPedidosSoporteGenerales() {
        def pedidosSoporte = []
        aplicaciones.each {
            pedidosSoporte.push([ nombreAplicacion: it.nombre, pedidosAplicacion: it.pedidosSoporte ])
        }
        pedidosSoporte
    }

    def obtenerPedidosSoporteMiembro(emailMiembro) {
        def miembro = obtenerMiembro(emailMiembro)
        def pedidosSoporte = []
        aplicaciones.each {
            def pedidosMiembro = it.obtenerPedidosSoporteMiembro(miembro)
            if (pedidosMiembro.size() > 0)
                pedidosSoporte.push([ nombreAplicacion: it.nombre, pedidosAplicacion: it.obtenerPedidosSoporteMiembro(miembro) ])
        }
        pedidosSoporte
    }

    def obtenerConversacionPedidoSoporteDeAplicacion(idPedido, nombreAplicacion) {
        def aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.obtenerConversacionPedido(idPedido)
    }

    def asignarPedidoSoporte(nombreAplicacion, idPedidoSoporte, emailMiembro) {
        MiembroEquipo miembro = obtenerMiembro(emailMiembro)
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.asignarPedidoSoporte(idPedidoSoporte, miembro)
    }

    def agregarTemaEnAplicacion(nombreTema, nombreAplicacion) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.agregarTema(nombreTema)
    }

    def borrarTemaDeAplicacion(nombreTema, nombreAplicacion) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.borrarTema(nombreTema)
    }

    def agregarRespuestaAutomatica(nombreAplicacion, nombreTema, tituloRespuesta) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.agregarRespuestaAutomatica(nombreTema, tituloRespuesta)
    }

    def borrarRespuestaAutomatica(nombreAplicacion, nombreTema, tituloRespuesta) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.borrarRespuestaAutomatica(nombreTema, tituloRespuesta)
    }

    def actualizarRespuestaAutomatica(nombreAplicacion, nombreTema, tituloRespuesta, mensaje, palabrasClave) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.actualizarRespuestaAutomatica(nombreTema, tituloRespuesta, mensaje, palabrasClave)
    }

    def actualizarPalabrasClave(nombreAplicacion, nombreTema, palabrasClave) {
        AplicacionCliente aplicacion = obtenerAplicacion(nombreAplicacion)
        aplicacion.actualizarPalabrasClave(nombreTema, palabrasClave)
    }
}
