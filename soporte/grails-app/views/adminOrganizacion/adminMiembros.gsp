<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
	</head>
	<body>
	    <h1>Administración de miembros</h1>
	    <g:each in="${miembros}">
            <p>Email: ${it.email}</p>
            <p>Password: ${it.password}</p>
            <p>Rol: ${it.describirRol()}</p>
        </g:each>
        <g:form>
            <g:hiddenField name="organizacion" value="${params.organizacion}"/>
            <g:actionSubmit value="Invitar" action="invitarMiembro"/><br>
        </g:form>
	</body>
</html>