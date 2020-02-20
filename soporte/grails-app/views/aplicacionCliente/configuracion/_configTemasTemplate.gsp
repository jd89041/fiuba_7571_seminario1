<div class="configGeneral">
    <h4>
        Temas
    </h4>
    <div class="card">
        <div class="card-body">
            <g:if test="${it.size() == 0}">
                No hay temas definidos!
            </g:if>
            <g:each var="tema" in="${it}">
                <p class="card-text">
                    <g:render template="/aplicacionCliente/configuracion/configTemaTemplate" bean="${tema}"/>
                </p>
            </g:each>
        </div>
        <div class="card-footer">
            <div class="row justify-content-end">
                <button type="button" class="btn btn-success" onclick="agregarTema('${nombreAplicacion}')">Agregar</button>
            </div>
        </div>
    </div>
</div>