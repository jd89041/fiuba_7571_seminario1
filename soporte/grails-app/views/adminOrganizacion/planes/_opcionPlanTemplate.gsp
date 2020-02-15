<div class="opcionPlan">
    <div class="card text-center" style="width: 18rem;">
        <h5 class="card-header">${it.nombre}</h5>
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <label>Cantidad de aplicaciones</label>
                ${it.cantidadMaxAplicaciones}
                <hr/>
                <label>Cantidad de miembros</label>
                ${it.cantidadMaxMiembros}
                <hr/>
                <label>Precio</label>
                ${it.precio}
            </ul>
        </div>
        <div class="card-footer">
            <button type="button" class="btn btn-primary" onclick="adquirirPlan('${it.nombre}')">Adquirir</button>
        </div>
    </div>
</div>