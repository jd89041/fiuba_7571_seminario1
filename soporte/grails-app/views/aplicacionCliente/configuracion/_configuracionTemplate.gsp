<div class="configuracionAplicacion">
    <h2>Configuración</h2>
    <button type="button" class="btn btn-success" onclick="guardarConfiguracion('${nombreAplicacion}')">Guardar  </button>
    <h4>Opciones generales</h4>
    <g:render template="/reglas/listaReglasTemplate" bean="${reglas}"/>
    <g:javascript>
        function agregarReglasNumericas(reglas) {
            $.each($(".reglaNumerica"), function() {
                reglas.push({ "activa": $(this).find('#activa').is(":checked"), "tipo": $(this).find('#tipo').val(), "nombre": $(this).find('#nombre').val(), "valor": $(this).find('#valor').val() });
            });
        }

        function agregarReglasRangoNumerico(reglas) {
            $.each($(".reglaRangoNumerico"), function() {
                reglas.push({
                    "activa": $(this).find('#activa').is(":checked"),
                    "tipo": $(this).find('#tipo').val(),
                    "nombre": $(this).find('#nombre').val(),
                    "minimo": $(this).find('#minimo').val(),
                    "maximo": $(this).find('#maximo').val()
                 });
            });
        }
    </g:javascript>
</div>