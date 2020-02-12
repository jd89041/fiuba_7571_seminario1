<g:applyLayout name="paneles">
    <content tag="panelCentral">
        <div class="row justify-content-center tamano-padre">
            <div class="centrado align-self-center">
                <h2>Bienvenido</h2>
                <p>
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseIngresar" aria-expanded="false" aria-controls="collapseIngresar">
                        Ingresar
                    </button>
                </p>
                <div class="collapse" id="collapseIngresar">
                    <div class="card card-body">
                        <g:render template="ingresarTemplate" />
                    </div>
                </div>
                <g:form controller="crearOrganizacion">
                    <g:actionSubmit value="Crear Organización" action="index"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
