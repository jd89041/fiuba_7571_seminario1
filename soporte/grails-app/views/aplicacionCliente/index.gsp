<g:applyLayout name="paneles">
    <content tag="panelEncabezado">
        Administración de aplicaciones
    </content>
    <content tag="panelCentral">
        <h3>Aplicaciones</h3>
        <g:each in="${aplicaciones}">
            <g:render template="aplicacionTemplate" bean="${it}"/>
        </g:each>
        <button type="button" class="btn btn-success" onclick="agregarAplicacion()">Agregar</button>
        <g:javascript>
            function agregarAplicacion() {
                var nombreAplicacion = prompt("Ingrese un nombre para la nueva aplicación");
                if(nombreAplicacion != null && nombreAplicacion != '') {
                    ejecutarLlamada("validarCreacionDeAplicacion",
                        {
                            nombreAplicacion: nombreAplicacion
                        },
                        function(respuesta) {
                            if (respuesta.limiteAlcanzado)
                                alert("No se pueden agregar más aplicaciones cliente. Mejore su plan!");
                            else {
                                if (respuesta.existe)
                                    alert("Ya existe una aplicación con ese nombre!");
                                else
                                {
                                    ocultarPanelDerecho();
                                    if (confirm("Desea crear la aplicación " + respuesta.nombreNuevaAplicacion + "?"))
                                        crearAplicacion(respuesta.nombreNuevaAplicacion);
                                }
                            }
                        }
                    );
                }
            }

            function crearAplicacion(nombreAplicacion) {
                ejecutarLlamada("crearAplicacion",
                    {
                        nombreAplicacion: nombreAplicacion
                    },
                    function(respuesta) {
                        window.location.href = respuesta.redirect;
                    }
                );
            }

            function invitarMiembro(nombreAplicacion, emailMiembro) {
                ejecutarLlamada("agregarMiembro",
                    {
                        nombreAplicacion: nombreAplicacion,
                        emailNuevoMiembro: emailMiembro
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                    }
                );
            }

            function removerMiembro(nombreAplicacion, emailMiembro) {
                ejecutarLlamada("removerMiembro",
                    {
                        nombreAplicacion: nombreAplicacion,
                        emailMiembro: emailMiembro
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                    }
                );
            }

            function mostrarMiembros(nombreAplicacion) {
                ejecutarLlamada("obtenerMiembros",
                    {
                        nombreAplicacion: nombreAplicacion
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                        mostrarPanelDerecho();
                    }
                );
            }

            function mostrarTemas(nombreAplicacion) {
                console.log("nombreAplicacion");
            }

            function mostrarConfiguracion(nombreAplicacion) {
                ejecutarLlamada("obtenerConfiguracion",
                    {
                        nombreAplicacion: nombreAplicacion
                    },
                    function(respuesta) {
                        $("#contenidoPanelDerecho").html(respuesta.html);
                        mostrarPanelDerecho();
                    }
                );
            }

            function guardarConfiguracion(nombreAplicacion) {
                if (confirm("Desea guardar la configuración?"))
                {
                    var configGeneral = obtenerConfigGeneral();
                    var reglas = [];
                    agregarReglasNumericas(reglas);
                    agregarReglasRangoNumerico(reglas);
                    var configuracion = {
                        reglas: reglas,
                        general: configGeneral
                    }
                    ejecutarLlamada("actualizarConfiguracion",
                        {
                            nombreAplicacion: nombreAplicacion,
                            configuracion: JSON.stringify(configuracion)
                        },
                        function(respuesta) {
                            $("#contenidoPanelDerecho").html(respuesta.html);
                            //mostrarPanelDerecho();
                        }
                    );
                }
            }
        </g:javascript>
	</content>
	<content tag="panelDerecho">
	    <div id="contenidoPanelDerecho"/>
	</content>
</g:applyLayout>