<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Perfil de ${miembro.email}</h1>
	    Rol: ${miembro.describirRol()}
	    <br>
        <g:if test="${miembro.tienePermiso('Total')}">
            <g:form>
                <g:hiddenField name="organizacion" value="${miembro.organizacion.nombre}"/>
                <g:actionSubmit value="Administrar Organización" action="irAdministrarOrganizacion"/>
            </g:form>
        </g:if>
        <br>
        <g:if test="${miembro.aplicaciones.size() > 0}">
            Aplicaciones
            <br>
            <g:each in="${miembro.aplicaciones}">
                <p>Aplicacion: ${it.nombre}</p>
            </g:each>
            <g:if test="${miembro.pedidosSoporte.size() > 0}">
                Pedidos de soporte
                <br>
                <g:each in="${miembro.pedidosSoporte}">
                    <p>Pedido de soporte de app: ${it.aplicacion.nombre}</p>
                    <p>Pedido de soporte del autor: ${it.emailAutor}</p>
                </g:each>
            </g:if>
            <g:else>
                No tiene pedidos de soporte asignados!
            </g:else>
        </g:if>
        <g:else>
            No está asignado a ninguna aplicación!
        </g:else>
	</body>
</html>