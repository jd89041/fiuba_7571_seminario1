<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Administración de miembros</h1>
	    <g:form>
            <g:hiddenField name="organizacion" value="${params.organizacion}"/>
            <g:actionSubmit value="Invitar" action="invitarMiembro"/><br>
        </g:form>
	    <g:each in="${miembros}">
            <p>Email: ${it.email}</p>
            <p>Password: ${it.password}</p>
            <p>Rol: ${it.describirRol()}</p>
        </g:each>
	</body>
</html>