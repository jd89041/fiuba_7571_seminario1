<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Tester para crear organizaciones</h1>
        <g:form controller="tester">
            Organización <br>
            <g:field type="text" name="organizacion" required=""/> <br>
            Aplicación Cliente <br>
            <g:field type="text" name="aplicacionCliente" required=""/> <br>
            Mail <br>
            <g:field type="email" name="email" required=""/> <br>
            Password<br>
            <g:field type="text" name="password" required=""/> <br>
            <g:actionSubmit value="Crear" action="crearOrganizacion"/>
        </g:form>
	</body>
</html>