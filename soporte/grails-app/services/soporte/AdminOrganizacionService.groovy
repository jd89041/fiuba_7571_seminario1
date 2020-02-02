package soporte

import grails.gorm.transactions.Transactional
import soporte.Organizacion

@Transactional
class AdminOrganizacionService {
    def confirmacionAltaOrganizacionService
    def confirmacionAltaMiembroService

    def agregarAplicacionCliente(nombreOrganizacion, nombreAplicacion, herramientaBots) {
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        AplicacionCliente aplicacionCliente = new AplicacionCliente()
        aplicacionCliente.nombre = nombreAplicacion
        aplicacionCliente.herramientaBots = herramientaBots != null
        aplicacionCliente.organizacion = organizacion
        organizacion.agregarAplicacion(aplicacionCliente)
        organizacion.save(failOnError: true)
    }

    def agregarMiembroEquipo(organizacion, email, password, rol) {
        MiembroEquipo miembroEquipo = new MiembroEquipo()
        miembroEquipo.organizacion = organizacion
        miembroEquipo.email = email
        miembroEquipo.password = password
        miembroEquipo.rol = Rol.findByNombre(rol)
        organizacion.agregarMiembro(miembroEquipo)
        confirmacionAltaMiembroService.borrar(organizacion.nombre)
    }

    def crearOrganizacionConAdmin(nombreOrganizacion, emailAdmin, passwordAdmin) {
        Organizacion organizacion = new Organizacion(nombreOrganizacion)
        agregarMiembroEquipo(organizacion, emailAdmin, passwordAdmin, Rol.ADMINISTRADOR)
        organizacion.save(failOnError: true)
        confirmacionAltaOrganizacionService.borrar(organizacion.nombre)
    }

    def actualizarPlan(nombreOrganizacion, nombrePlanOferta) {
        def planOferta = PlanOferta.findByNombre(nombrePlanOferta)
        Organizacion organizacion = Organizacion.findByNombre(nombreOrganizacion)
        organizacion.adquirirPlan(planOferta)
    }
}
