package soporte.reglas.asignacion

import soporte.Rol

class RestriccionRol extends ReglaAsignacion {
    String nombreRol

    static constraints = {
        nombreRol nullable: false, blank: false
    }

    @Override
    def obtenerNombre() {
        "Restricción de rol"
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
