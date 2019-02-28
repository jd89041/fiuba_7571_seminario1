<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="ingresar.titulo"/></title>
	</head>
	<body>
	    <h1><g:message code="ingresar.error.organizacion.no.existe"/></h1>
        <g:form controller="crearOrganizacion">
            <g:hiddenField type="string" name="organizacion" value="${organizacion}"/>
            <g:actionSubmit value="Crear Organización" action="index"/>
        </g:form>
	</body>
</html>