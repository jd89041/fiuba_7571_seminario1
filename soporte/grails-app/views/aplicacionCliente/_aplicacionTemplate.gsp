<div class="aplicacion">
    <div class="card mb-3" style="min-width: 540px;">
        <div class="row">
            <div class="col">
                <div class="card-body">
                    <h5 class="card-title">${it.nombre}</h5>
                </div>
            </div>
            <div class="col-auto ml-auto">
                <div class="row row-cols-3 align-items-center" style="height: 100%;">
                    <div class="col">
                        <button type="button" class="btn btn-primary" onclick="mostrarMiembros('${it.nombre}')">Miembros</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-primary" onclick="mostrarTemas('${it.nombre}')">Temas</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-primary" onclick="mostrarConfiguracion('${it.nombre}')">Configuración</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>