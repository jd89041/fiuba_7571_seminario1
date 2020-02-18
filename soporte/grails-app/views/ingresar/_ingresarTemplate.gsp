<div class="ingresar">
    <g:form controller="ingresar">
        <div class="form-group">
            <label for="nombreOrganizacion">Organización</label>
            <p>
                <g:field type="text" name="nombreOrganizacion" required=""/>
            </p>
        </div>
        <div class="collapse" id="collapseCredenciales">
            <div class="form-group">
                <label for="emailMiembro">Email</label>
                <p>
                    <g:field type="email" name="email" required=""/>
                </p>
                <label for="password">Contraseña</label>
                <p>
                    <g:field type="password" name="password" required=""/>
                </p>
            </div>
        </div>
        <button class="btn btn-primary" type="button" onclick="verificarIngreso()">
            Confirmar
        </button>
    </g:form>

    <g:javascript>
        function verificarIngreso() {
            var nombreOrganizacion = $('#nombreOrganizacion').val();
            if (!nombreOrganizacion)
                alert("Debe ingresar el nombre de su organización para continuar!");
            else {
                var email = $("#email").val();
                if (email) {
                    var password = $("#password").val();
                    if (!password)
                        alert("Debe ingresar su contraseña para continuar");
                    else
                        verificarCredencialesIngreso(nombreOrganizacion, email, password);
                }
                else
                {
                    if ($("#nombreOrganizacion").prop("disabled"))
                        alert("Debe ingresar su email para continuar");
                    else
                        verificarOrganizacionIngreso(nombreOrganizacion, email, password);
                }
            }
        }

        function verificarOrganizacionIngreso(nombreOrganizacion, email, password) {
            $.ajax(
                {
                    url: "ingresar/verificarOrganizacion",
                    data: {
                        nombreOrganizacion: nombreOrganizacion
                    }
                })
                .success(function(respuesta) {
                    if (respuesta == 0) {
                        $("#nombreOrganizacion").prop("disabled", true);
                        $("#collapseCredenciales").collapse();
                    } else {
                        alert("La organización " + nombreOrganizacion + " no existe!");
                    }
                }
            );
        }

        function verificarCredencialesIngreso(nombreOrganizacion, email, password) {
            $.ajax(
                {
                    url: "ingresar/verificarCredenciales",
                    data: {
                        nombreOrganizacion: $('#nombreOrganizacion').val(),
                        email: $("#email").val(),
                        password: $("#password").val()
                    }
                })
                .success(function(respuesta) {
                    switch(respuesta.credencial) {
                        case "ok":
                            window.location.href = respuesta.redirect;
                        break;
                        case "invalida":
                            alert("La contraseña es inválida!");
                        break;
                        case "inexistente":
                            alert("El miembro " + email + " no existe en la organización " + nombreOrganizacion);
                        break;
                    }
                }
            );
        }
    </g:javascript>
</div>