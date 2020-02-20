<div class="card">
    <div class="card-header">
        ${it.titulo}
    </div>
    <div class="card-body">
        <label>Mensaje</label>
        <div class="row">
            <input type="text" class="form-control" id="msg-ra-${it.titulo}" value="${it.mensaje}">
        </div>
        <label>Palabras clave</label>
        <div class="row">
            <input type="text" class="form-control" id="pc-ra-${it.titulo}" value="${it.obtenerListaPalabrasClave()}">
        </div>
    </div>
    <div class="card-footer">
        <div class="row justify-content-end">
            <div-class="col">
                <button type="button" class="btn btn-success" onclick="guardarRespuestaAutomatica('${nombreAplicacion}', '${nombreTema}', '${it.titulo}')">Actualizar</button>
            </col>
            <div-class="col">
                <button type="button" class="btn btn-danger" onclick="borrarRespuestaAutomatica('${nombreAplicacion}', '${nombreTema}', '${it.titulo}')">Borrar</button>
            </col>
        </div>
    </div>
</div>
