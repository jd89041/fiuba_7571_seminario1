<div class="listaPlanes">
    <div class="row justify-content-center">
        <g:each in="${it}">
            <div class="col-auto" style="padding: 5px;">
                <g:render template="planes/opcionPlanTemplate" bean="${it}"/>
            </div>
        </g:each>
    </div>
</div>