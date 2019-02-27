package soporte

class ConfirmacionAltaMiembro {
    String nombreOrganizacion
    String email
    String ticket
    Rol rol

    static mapping = {
        id generator: 'assigned', name: 'email'
    }

    static constraints = {
        nombreOrganizacion blank: false
        email blank: false
        ticket nullable: true
    }
}
