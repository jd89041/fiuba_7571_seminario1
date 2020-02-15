<g:applyLayout name="paneles">
    <content tag="panelCentral">
	    <div class="container" style="height: inherit;">
            <div class="row justify-content-center">
                <h1>Administración de planes</h1>
            </div>
            <div id="planActual">
                <g:render template="planes/planActualTemplate" bean="${planActual}"/>
            </div>
            <div class="row justify-content-center">
                <g:if test="${planes.size() > 0}">
                    <h1>Planes disponibles</h1>
                    <div id="listaPlanesOferta">
                        <g:render template="planes/listaPlanesTemplate" bean="${planes}"/>
                    </div>
                </g:if>
            </div>
        </div>

        <g:javascript>
            function adquirirPlan(nombrePlan) {
                if (confirm("Desea adquirir el plan: " + nombrePlan + "?")) {
                    $.ajax(
                        {
                            url: "adquirirPlan",
                            data: { plan: nombrePlan }
                        })
                        .success(function(respuesta) {
                            $("#planActual").html(respuesta.html.planActual);
                            $("#listaPlanesOferta").html(respuesta.html.planesOferta);
                        }
                    );
                }
            }
        </g:javascript>

	</content>
</g:applyLayout>