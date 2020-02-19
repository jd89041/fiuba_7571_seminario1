<div class="card">
    <div class="card-body">
        <div class="row">
            Autor: ${it.autor.nombre}
        </div>
        <div class="row">
            <g:if test="${!it.estaAsignado()}">
                No está asignado
            </g:if>
            <g:else>
                <g:if test="${session.emailMiembro != it.miembro.email}">
                    Asignado a ${it.miembro.email}
                </g:if>
            </g:else>
        </div>
        <div class="row">
            <div class="col-auto ml-auto">
                <button type="button" class="btn btn-success" onclick="mostrarConversacion('${it.aplicacion.nombre}', ${it.id})">Ver</button>
            </div>
        </div>
    </div>

</div>