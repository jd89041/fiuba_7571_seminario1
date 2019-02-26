package soporte

class ConfirmacionAltaOrganizacion {

    static final int OPERACION_ENVIAR = 0
    static final int OPERACION_REENVIAR = 1
    static final int OPERACION_NO_DISPONIBLE = 2

    String nombreOrganizacion
    String email
    String ticket

    static mapping = {
        id generator: 'assigned', name: 'nombreOrganizacion'
    }

    static constraints = {
        nombreOrganizacion blank: false
        email blank: false
        ticket nullable: true
    }
}