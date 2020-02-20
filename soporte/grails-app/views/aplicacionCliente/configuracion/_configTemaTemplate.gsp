<div class="card">
    <div class="card-header">
        ${it.nombre}
    </div>
    <div class="card-body">
        <div class="card">
            <div class="card-header">
                Palabras clave
            </div>
            <div class="card-body">
                <div class="row">
                    <input type="text" class="form-control" id="pc-${it.nombre}" value="${it.obtenerListaPalabrasClave()}">
                </div>
            </div>
            <div class="card-footer">
                <div class="row justify-content-end">
                    <button type="button" class="btn btn-success" onclick="actualizarPalabrasClave('${nombreAplicacion}', '${it.nombre}')">Actualizar</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card-body">
        <div class="card">
            <div class="card-header">
                Respuestas automaticas
            </div>
            <div class="card-body">
                <div class="row">
                    <g:if test="${!it.respuestasAutomaticas}">
                        No disponibles
                    </g:if>
                    <g:each var="respuestaAutomatica" in="${it.respuestasAutomaticas}">
                        <g:render template="/aplicacionCliente/configuracion/respuestaAutomaticaTemplate" bean="${respuestaAutomatica}" model='['nombreTema': "${it.nombre}"]'/>
                    </g:each>
                </div>
            </div>
            <div class="card-footer">
                <div class="row justify-content-end">
                    <button type="button" class="btn btn-success" onclick="agregarRespuestaAutomatica('${nombreAplicacion}', '${it.nombre}')">Agregar</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card-footer">
        <div class="row justify-content-end">
            <button type="button" class="btn btn-danger" onclick="borrarTema('${nombreAplicacion}', '${it.nombre}')">Borrar</button>
        </div>
    </div>
</div>
