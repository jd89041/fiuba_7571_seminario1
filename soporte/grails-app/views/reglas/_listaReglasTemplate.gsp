<div class="listaReglas">
    <h4>Reglas</h4>
    <div class="card">
        <g:each in="${it}">
            <div class="col-auto">
                <g:render template="/reglas/reglaTemplate" bean="${it}"/>
            </div>
        </g:each>
    </div>
</div>