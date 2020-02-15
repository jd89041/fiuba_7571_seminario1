<div class="planActual">
    <g:if test="${!it.asignado()}">
        <div class="alert alert-danger text-center" role="alert">
            <h2>La organización no dispone de un plan</h2>
        </div>
    </g:if>
    <g:else>
        <div class="card mb-3 text-center">
            <div class="card-header">
                <h2 class="card-title">Plan actual</h2>
            </div>
            <div class="card-body">
                <h3 class="card-title">${it.nombre}</h3>
                <hr/>
                <p class="card-text">Cantidad de miembros: ${it.organizacion.miembros.size()}/${it.cantidadMaxMiembros}</p>
                <p class="card-text">Cantidad de aplicaciones: ${it.organizacion.aplicaciones.size()}/${it.cantidadMaxAplicaciones}</p>
            </div>
        </div>
    </g:else>
</div>