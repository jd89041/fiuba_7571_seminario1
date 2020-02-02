package soporte

import soporte.notificaciones.Notificacion

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
        //notificar() // msg de pedido removido
        save(failOnError: true)
    }

    def agregarPedidoSoporte(pedidoSoporte) {
        addToPedidosSoporte(pedidoSoporte)
        //notificar() // msg de pedido agregado
        save(failOnError: true)
    }

    def notificar(notificacion) {
        addToNotificaciones(notificacion)
        save(failOnError: true)
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
    }

    def tieneNotificacionesNoLeidas() {
        notificaciones.any {
            !it.leida
        }
    }
}
