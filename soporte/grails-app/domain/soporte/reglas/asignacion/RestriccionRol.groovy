package soporte.reglas.asignacion

import soporte.Rol

class RestriccionRol extends ReglaAsignacion {

    static final String NOMBRE = "Restricción de rol"

    String nombreRol

    static constraints = {
        nombreRol nullable: false, blank: false
    }

    @Override
    def obtenerTipo() {
        "alfabetica"
    }

    @Override
    def obtenerNombre() {
        NOMBRE
    }

    @Override
    def obtenerDescripcion() {
        "Limita el rol del miembro a los que pueden asignarse los pedidos de soporte"
    }

    @Override
    def aplicar(pedidoSoporte, miembros) {
        miembros.findAll {
            it.rol.nombre == nombreRol
        }
    }
}
