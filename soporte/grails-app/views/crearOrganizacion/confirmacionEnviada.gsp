<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title><g:message code="crear.organizacion.titulo"/></title>
    </head>
    <body>
        <h1><g:message code="crear.organizacion.titulo"/></h1>
        <h1><g:message code="crear.organizacion.enviada" args="["${organizacion}", "${email}"]"/></h1>
        <a href="${createLink(action: 'enviarEmail', params: [organizacion: organizacion])}">Reenviar</a><br>
        <a href="${createLink(action: 'modificarEmail', params: [organizacion: organizacion])}">Modificar</a><br>
    </body>
</html>