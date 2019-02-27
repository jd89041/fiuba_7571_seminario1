<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Perfil</h1>
	    Email: ${miembro.email}
	    <br>
	    Tipo de permiso: ${miembro.tipoPermiso()}
	    <br>
        <a href="${createLink(controller: 'index')}">Pedidos de soporte</a><br>
        <g:if test="${miembro.esAdmin}">
            <a href="${createLink(controller: 'configuracionOrganizacion')}">Configuración Organización</a><br>
        </g:if>
	</body>
</html>