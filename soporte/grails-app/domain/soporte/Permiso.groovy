package soporte

class Permiso {
    static final String TOTAL = "Total"
    static final String PERFIL = "Perfil"
    static final String PEDIDOS_SOPORTE = "PedidosSoporte"

    String nombre

    static mapping = {
        version false
    }

    static constraints = {
        nombre blank: false
    }
}
