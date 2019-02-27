package soporte

class Permiso {

    String nombre

    static mapping = {
        version false
    }

    static constraints = {
        nombre blank: false
    }
}
