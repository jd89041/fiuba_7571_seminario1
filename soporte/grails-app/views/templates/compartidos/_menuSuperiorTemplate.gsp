<div class="menu-superior">
    <nav class="navbar navbar-dark bg-primary row">
        <div class="col-2 mr-auto">
            ${session.emailMiembro}
        </div>
        <div class="col-auto">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownNotificaciones" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Notificaciones <span class="badge badge-danger" id="nroNotificacionesNoLeidas"></span>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownNotificaciones" id="notificaciones" style="width: max-content; max-height: 400px; overflow-y: scroll;">
                    No hay notificaciones!
                </div>
            </div>
        </div>
        <div class="col-auto">
            <div class="dropdown">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuSup" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                   Menú
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuSup">
                    <g:form controller="pantallaTareas">
                        <g:actionSubmit class="dropdown-item" value="Tareas" action="index"/>
                    </g:form>
                    <g:form controller="perfil">
                        <g:actionSubmit class="dropdown-item" value="Perfil" action="index"/>
                    </g:form>
                    <g:if test="${session.rolMiembro == "Administrador"}">
                        <g:form controller="AdminOrganizacion">
                            <g:actionSubmit class="dropdown-item" value="Administración" action="index"/>
                        </g:form>
                    </g:if>
                    <div class="dropdown-divider"></div>
                    <g:form controller="perfil">
                        <g:actionSubmit class="dropdown-item" value="Desconectarse" action="desconectar"/>
                    </g:form>
                </div>
            </div>
        </div>
    </nav>
</div>

<g:javascript>
    var idTimeoutEnCurso = -1;

    $(document).ready(function() {
        obtenerNotificaciones();
    });

    function obtenerNotificaciones() {
        actualizarMenu("obtenerNotificaciones");
    }

    function leerNotificacion(id) {
        actualizarMenu("leerNotificacion", { notificacionId: id });
    }

    function borrarNotificacion(id) {
        actualizarMenu("borrarNotificacion", { notificacionId: id });
    }

    function actualizarMenu(accion, params) {
        if (idTimeoutEnCurso != -1)
            clearTimeout(idTimeoutEnCurso)
        ejecutarLlamada("/menuSuperior/" + accion,
            params,
            function(respuesta) {
                $("#notificaciones").html(respuesta.htmlNotificaciones);
                var noLeidas = respuesta.nroNotificacionesNoLeidas;
                $("#nroNotificacionesNoLeidas").html(noLeidas ? noLeidas : "");
                idTimeoutEnCurso = setTimeout(obtenerNotificaciones, 10000);
            }
        );
    }
</g:javascript>