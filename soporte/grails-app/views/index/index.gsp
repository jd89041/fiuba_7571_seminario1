<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Inicio</h1>
	    <g:form controller="ingresar">
            <g:actionSubmit value="Ingresar" action="index"/>
        </g:form>
        <g:form controller="crearOrganizacion">
            <g:actionSubmit value="Crear Organización" action="index"/>
        </g:form>
	</body>
</html>
