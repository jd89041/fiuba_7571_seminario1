<div class="conversacionPedidoSoporte" style="height: inherit;">
    <h2>Conversación</h2>
    <div class="card">
        <div class="card-body">
            <div class="row" id="contenedorConversacion" style="width: 40vw; height: 70vh; overflow-y:scroll;">
                <g:each var="mensaje" in="${conversacion}">
                    <g:render template="mensajePedidoSoporteTemplate" bean="${mensaje}"/>
                </g:each>
            </div>
            <g:if test="${puedeResponder}">
                <div class="row justify-content-end">
                    <g:form>
                        <g:field onkeyup="manejarBotonEnviar()" type="text" name="respuestaPedidoSoporte"/>
                        <button type="button" id="botonEnviarConversacion" onclick="enviarRespuesta('${nombreAplicacion}', ${idPedido})" disabled>Responder</button>
                    </g:form>
                </div>
            </g:if>
            <g:if test="${puedeAsignar}">
                <nav class="navbar row justify-content-end">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMiembros" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Asignar
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMiembros">
                            <g:each in="${miembrosAplicacion}">
                                <button class="dropdown-item" type="button" onclick="asignarPedido('${nombreAplicacion}', ${idPedido}, '${it.email}')">${it.email}</button>
                            </g:each>
                        </div>
                    </div>
                </nav>
            </g:if>
            <div class="row justify-content-end">
                <button type="button" onclick="cerrarHistorial()">Cerrar</button>
            </div>
        </div>
    </div>
</div>
<g:javascript>
    $(document).ready(function() {
        $("#contenedorConversacion").scrollTop($(document).height() * 2);
    });

    function manejarBotonEnviar() {
        if ($("#respuestaPedidoSoporte").val())
            $("#botonEnviarConversacion").attr("disabled", false);
        else
            $("#botonEnviarConversacion").attr("disabled", true);
    }
</g:javascript>