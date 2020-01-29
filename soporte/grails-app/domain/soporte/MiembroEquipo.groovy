package soporte

class MiembroEquipo {

    static final int CREDENCIALES_OK = 0
    static final int CREDENCIALES_ERROR = 1
    static final int CREDENCIALES_INEXISTENTES = 2

    String email
    String password
    Rol rol
    List<String> especialidades

    static hasMany = [pedidosSoporte: PedidoSoporte]
    static belongsTo = [organizacion: Organizacion]

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

    def puedeAsignarPedidos() {
        // en base a reglas, devolver si puede o no
    }

    def removerPedidoSoporte(pedidoSoporte) {
        pedidosSoporte -= pedidoSoporte
    }

    def agregarPedidoSoporte(pedidoSoporte) {
        pedidosSoporte.push(pedidoSoporte)
    }

    def notificar() {
        // usando el mail notificar al user?
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
}
