<g:each in="${miembros}">
    <g:render template="/miembros/miembroTemplate" bean="${it}"/>
</g:each>