package soporte

class AplicacionCliente {
    String nombre
    boolean herramientaBots

    static belongsTo = [organizacion: Organizacion]

    static mapping = {
        id generator: 'assigned', name: 'nombre'
    }

    static constraints = {
        nombre blank: false
    }
}
