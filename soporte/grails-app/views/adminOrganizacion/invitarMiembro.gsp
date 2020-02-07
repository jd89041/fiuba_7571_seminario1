<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="aplicacion.titulo"/></title>
	</head>
	<body>
	    <h1>Invitar de miembro</h1>
	    <g:form>
            <g:field type="string" name="email" required=""/> Email<br>
            Rol: <g:select name="rol" from="${roles}" optionValue="nombre" optionKey="id"/>
            <g:actionSubmit value="OK" action="enviarInvitacion"/><br>
        </g:form>
	</body>
</html>