<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1>Perfil de ${miembro.email}</h1>
	    <!--  info general -->
	    Rol: ${miembro.describirRol()} <br>
	    <g:form>
            <g:actionSubmit value="Desconectarse" action="desconectar"/>
        </g:form>
        <g:if test="${miembro.tienePermiso('Total')}">
            <g:form>
                <g:hiddenField name="organizacion" value="${miembro.organizacion.nombre}"/>
                <g:actionSubmit value="Administrar Organización" action="irAdministrarOrganizacion"/>
            </g:form>
        </g:if>

        <!-- aplicaciones -->
        <g:if test="${miembro.aplicaciones.size() > 0}">
            <h2>Aplicaciones</h2>
            <g:each in="${miembro.aplicaciones}">
                <p>Aplicacion: ${it.nombre}</p>
            </g:each>
            <g:if test="${miembro.pedidosSoporte.size() > 0}">
                <h2>Pedidos de soporte</h2>
                <g:each in="${miembro.pedidosSoporte}">
                    <p>Pedido de soporte de app: ${it.aplicacion.nombre}</p>
                    <p>Pedido de soporte del autor: ${it.emailAutor}</p>
                </g:each>
            </g:if>
            <g:else>
                <p>No tiene pedidos de soporte asignados!</p>
            </g:else>
        </g:if>
        <g:else>
            <p>No está asignado a ninguna aplicación!</p>
        </g:else>
	</body>
</html>