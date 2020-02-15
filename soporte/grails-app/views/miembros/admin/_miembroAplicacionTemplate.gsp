<div class="miembroAplicacion">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${it.email}</h5>
            <g:if test="${activo}">
                <button type="button" class="btn btn-danger" onclick="completarRemocion('${it.email}')">Remover</button>
            </g:if>
            <g:else>
                <button type="button" class="btn btn-success" onclick="completarInvitacion('${it.email}')">Invitar</button>
            </g:else>
        </div>
    </div>
</div>