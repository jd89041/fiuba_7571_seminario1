<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="ingresar.titulo"/></title>
	</head>
	<body>
	    <h1><g:message code="ingresar.nombre.organizacion"/></h1>
	    <g:form>
	        <g:field type="string" name="organizacion" required=""/>
      		<g:actionSubmit value="OK" action="verificarOrganizacion"/>
	    </g:form>
	    <meta name="layout" content="main"/>
	</body>
</html>