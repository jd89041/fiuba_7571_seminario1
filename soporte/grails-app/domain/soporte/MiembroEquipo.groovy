package soporte

import soporte.notificaciones.Notificacion
import soporte.notificaciones.pedidos_soporte.NotificacionAsignacionDePedidoSoporte
import soporte.notificaciones.plan.NotificacionActualizacionPlan

class MiembroEquipo {

    static final int CREDENCIALES_OK = 0
    static final int CREDENCIALES_ERROR = 1
    static final int CREDENCIALES_INEXISTENTES = 2

    String email
    String password
    Rol rol
    List<String> especialidades

    static hasMany = [pedidosSoporte: PedidoSoporte, aplicaciones: AplicacionCliente, notificaciones:Notificacion]
    static belongsTo = [organizacion: Organizacion, aplicaciones: AplicacionCliente]

    static mapping = {
       id generator: 'assigned', name: 'email'
    }

    static constraints = {
        email blank: false
        password blank: false
        rol nullable: false
    }

    def describirRol() {
        rol.getDescripcion()
    }

    def tienePermiso(permiso) {
        rol.tienePermiso(permiso)
    }

    def credencialesValidas(password) {
        this.password == password
    }

    def esEspecialista(tema) {
        tema in especialidades
    }

    def puedeAsignarPedidoSoporte(pedidoSoporte) {
        // en base a reglas, devolver si puede o no
        true
    }

    def removerPedidoSoporte(pedidoSoporte) {
        removeFromPedidosSoporte(pedidoSoporte)
        notificar(new NotificacionActualizacionPlan(pedidoSoporte.aplicacion.nombre))
        save(failOnError: true)
    }

    def agregarPedidoSoporte(pedidoSoporte) {
        addToPedidosSoporte(pedidoSoporte)
        notificar(new NotificacionAsignacionDePedidoSoporte(pedidoSoporte.aplicacion.nombre))
        save(failOnError: true)
    }

    def notificar(notificacion) {
        if (notificacion.puedeEnviarseAlRol(rol)) {
            addToNotificaciones(notificacion)
            save(failOnError: true)
        }
        // usando el mail notificar al usuario, si lo tiene disponible
        // quizás un proceso pueda indicar en pantalla si recibe notificaciones el miembro q esté logeado!
    }

    def asignar(pedidoSoporte) {
        if (puedeAsignarPedidos()) {
            agregarPedidoSoporte(pedidoSoporte)
            notificar()
            return true
        }
        return false
    }

    def leerNotificacion(notificacionId) {
        def notificacion = notificaciones.find { it.id == notificacionId }
        notificacion.leer()
    }

    def borrarNotificacion(notificacionId) {
        def notificacion = notificaciones.find { it.id == notificacionId }
        removeFromNotificaciones(notificacion)
        notificacion.borrar()
        save(failOnError: true)
    }

    def obtenerNotificacionesNoLeidas() {
        notificaciones.findAll {
            !it.leida
        }
    }

    def obtenerPedidosSoporteDeAplicacion(aplicacion) {
        pedidosSoporte.findAll {
            it.aplicacion.nombre == aplicacion.nombre
        }
    }

    def conoceTemasDelPedidoSoporte(pedidoSoporte) {
        especialidades.findAll {
            it in pedidoSoporte.etiquetas
        }.size() > 0
    }

    def estaTrabajandoEnPedidoSoporte(pedidoSoporte) {
        pedidosSoporte.any {
            it == pedidoSoporte
        }
    }

    def estaTrabajandoEnPedidoSoporteConId(pedidoSoporteId) {
        pedidosSoporte.any {
            it.id == pedidoSoporteId
        }
    }

    def obtenerPedidoSoporteConId(id) {
        pedidosSoporte.find {
            it.id == id
        }
    }

    def obtenerPedidosSoporte() {
        def pedidos = []
        this.pedidosSoporte.each {
            pedidos.push([ nombreAplicacion: it.aplicacion.nombre, pedidosAplicacion: it.pedidosSoporte ])
        }
        pedidos
    }
}
