package soporte

import grails.gorm.transactions.Transactional
import soporte.reglas.Regla
import soporte.reglas.asignacion.CantidadMaximaPedidosAplicacion
import soporte.reglas.asignacion.CantidadMaximaPedidosTotales
import soporte.reglas.asignacion.RestriccionRol
import soporte.reglas.etiquetado.MinimaCantidadOcurrencias
import soporte.reglas.respuesta.BandaHoraria

interface IReglaNumerica {
    int obtenerValor()
    void setValor(int valor)
}

interface IReglaRangoNumerico {
    int obtenerMinimo()
    int obtenerMaximo()
    void setMinimo(int minimo)
    void setMaximo(int maximo)
}

@Transactional
class ReglaService {

    def generarReglasDisponibles() {
        [
            new CantidadMaximaPedidosTotales(),
            new CantidadMaximaPedidosAplicacion(),
            new BandaHoraria()
        ]
    }

    def obtenerTodasLasReglas(reglasActivas) {
        def nombreReglasActivas = reglasActivas.collect {
            it.obtenerNombre()
        }
        def reglasDisponibles = generarReglasDisponibles()
        def reglasInactivas = reglasDisponibles.findAll {
            !(it.obtenerNombre() in nombreReglasActivas)
        }
        reglasActivas + reglasInactivas
    }

    def actualizarRegla(aplicacion, regla, nuevosValores) {
        regla.setActiva(nuevosValores.activa)
        if (regla.instanceOf(IReglaNumerica)) {
            IReglaNumerica reglaNumeral = regla
            reglaNumeral.setValor(nuevosValores.valor as int)
        } else if (regla.instanceOf(IReglaRangoNumerico)) {
            IReglaRangoNumerico reglaRangoNumerico = regla
            reglaRangoNumerico.setMinimo(nuevosValores.minimo as int)
            reglaRangoNumerico.setMaximo(nuevosValores.maximo as int)
        }
    }

    def crearReglaNumerica(definicionRegla) {
        IReglaNumerica nuevaRegla
        switch(definicionRegla.nombre) {
            case CantidadMaximaPedidosTotales.NOMBRE:
                nuevaRegla = new CantidadMaximaPedidosTotales()
                break;
            case CantidadMaximaPedidosAplicacion.NOMBRE:
                nuevaRegla = new CantidadMaximaPedidosAplicacion()
                break;
        }
        nuevaRegla.setValor(definicionRegla.valor as int)
        nuevaRegla
    }

    def crearReglaRangoNumerico(definicionRegla) {
        IReglaRangoNumerico nuevaRegla
        switch(definicionRegla.nombre) {
            case BandaHoraria.NOMBRE:
                nuevaRegla = new BandaHoraria()
            break;
        }
        nuevaRegla.setMinimo(definicionRegla.minimo as int)
        nuevaRegla.setMaximo(definicionRegla.maximo as int)
        nuevaRegla
    }

    def agregarRegla(aplicacion, definicionRegla) {
        Regla nuevaRegla
        switch(definicionRegla.tipo) {
            case "numeral":
                nuevaRegla = crearReglaNumerica(definicionRegla)
                break;
            case "rango_numerico":
                nuevaRegla = crearReglaRangoNumerico(definicionRegla)
                break;
        }
        nuevaRegla.setActiva(definicionRegla.activa)
        aplicacion.agregarRegla(nuevaRegla)
    }

    def actualizarReglas(aplicacion, reglas) {
        reglas.each {
            def regla = aplicacion.obtenerRegla(it.nombre)
            if (regla)
                actualizarRegla(aplicacion, regla, it)
            else
                agregarRegla(aplicacion, it)
        }
    }
}
