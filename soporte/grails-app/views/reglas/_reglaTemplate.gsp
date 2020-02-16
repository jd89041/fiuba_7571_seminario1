<g:applyLayout name="reglas/regla">
    <content tag="titulo">
        <div data-toggle="tooltip" title="${it.obtenerDescripcion()}" style="width: 250px;">
            ${it.obtenerNombre()}
        </div>
    </content>
    <content tag="parametros">
        <g:hiddenField name="tipo" value="${it.obtenerTipo()}"/>
        <g:hiddenField name="nombre" value="${it.obtenerNombre()}"/>
        <div class="col-auto">
            <g:if test="${it.obtenerTipo() == 'numeral'}">
                <g:render template="/reglas/reglaNumericaTemplate" bean="${it}"/>
            </g:if>
            <g:elseif test="${it.obtenerTipo() == 'rango_numerico'}">
                <g:render template="/reglas/reglaRangoNumericoTemplate" bean="${it}"/>
            </g:elseif>
        </div>
        <label>Activo</label>
        <input type="checkbox" id="activa"
            <g:if test="${it.activa}">
                checked
            </g:if>
        >
    </content>
</g:applyLayout>