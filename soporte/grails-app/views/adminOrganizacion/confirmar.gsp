<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="crear.organizacion.titulo"/></title>
	</head>
	<body>
	    <h1><g:message code="crear.organizacion.msg.finalizando"/></h1>
        <h1>Resumen</h1>
        <h1>Invitado</h1>
        <b>Nombre de la organización:</b> ${organizacion}<br>
        <b>Email del solicitante:</b> ${email}<br>
        <b>Rol asignado:</b> ${rol.nombre}<br>
        <g:form>
            <g:hiddenField type="text"  name="organizacion" value="${organizacion}"/>
            <g:hiddenField type="email" name="email" value="${email}"/>
            <g:hiddenField name="rol" value="${rol.nombre}"/>
            <g:message code="crear.organizacion.msg.password"/><br>
            <g:field type="password" name="password" required=""/><br/>
            <g:actionSubmit value="Finalizar" action="finalizar"/>
        </g:form>
	</body>
</html>