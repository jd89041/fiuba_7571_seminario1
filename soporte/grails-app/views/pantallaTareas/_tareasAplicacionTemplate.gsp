<div class="card">
    <div class="card-header">
        ${it.nombreAplicacion}
    </div>
    <div class="card-body">
        <g:each in="${it.pedidosAplicacion}">
            <g:render template="/pedidoSoporte/pedidoSoporteTemplate" bean="${it}"/>
        </g:each>
    </div>
</div>