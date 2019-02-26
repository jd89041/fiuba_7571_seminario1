<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="ingresar.titulo"/></title>
	</head>
	<body>
	    <h1><g:message code="ingresar.credenciales.descripcion"/></h1>
	    <g:form>
	        <g:field type="string" name="email" required=""/> Email<br/>
	        <g:field type="password" name="password" required=""/> Password<br/>
	        <g:hiddenField name="organizacion" value="${organizacion}"/>
      		<g:actionSubmit value="OK" action="verificarCredenciales"/>
	    </g:form>
	</body>
</html>