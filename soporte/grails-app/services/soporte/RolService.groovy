package soporte

import grails.gorm.transactions.Transactional

@Transactional
class RolService {
    static final String ROL_POR_DEFECTO = Rol.AGENTE

    def obtener(nombreRol) {
        def rol = Rol.findByNombre(nombreRol)
        rol ? rol : Rol.findByNombre(ROL_POR_DEFECTO)
    }

    def listar() {
        Rol.list()
    }
}
