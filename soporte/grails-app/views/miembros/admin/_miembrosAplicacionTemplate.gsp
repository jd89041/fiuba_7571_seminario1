<div class="miembrosAplicacion">
    <h2>Miembros</h2>
    <div class="card">
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <g:if test="${activos.size() > 0}">
                    <li class="list-group-item">
                        <h5 class="card-title">Activos</h5>
                        <g:each in="${activos}">
                            <g:render template="/miembros/admin/miembroAplicacionTemplate" bean="${it}" model="['activo': true]"/>
                        </g:each>
                    </li>
                </g:if>
                <g:if test="${invitables.size() > 0}">
                    <li class="list-group-item">
                        <h5 class="card-title">Disponibles</h5>
                        <g:each in="${invitables}">
                            <g:render template="/miembros/admin/miembroAplicacionTemplate" bean="${it}" model="['activo': false]"/>
                        </g:each>
                    </li>
                </g:if>
            </ul>
        </div>
    </div>
    <g:javascript>
        function completarInvitacion(emailMiembro) {
            if (confirm("Desea invitar al usuario " + emailMiembro + " a la aplicación ${params.nombreAplicacion}?"))
                invitarMiembro("${params.nombreAplicacion}", emailMiembro);
        }

        function completarRemocion(emailMiembro) {
            if (confirm("Desea remover al usuario " + emailMiembro + " de la aplicación ${params.nombreAplicacion}?"))
                removerMiembro("${params.nombreAplicacion}", emailMiembro);
        }
    </g:javascript>
</div>