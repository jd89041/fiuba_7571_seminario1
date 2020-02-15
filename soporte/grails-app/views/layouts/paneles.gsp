<g:applyLayout name="main">
    <body>
        <div class="container tamano-padre">
            <div class="row justify-content-center">
                <h1>
                    <g:pageProperty name="page.panelEncabezado"/>
                </h1>
            </div>
            <div class="row tamano-padre">
                <div id="pIzquierdo" class="tamano-padre col-auto" style="visibility: hidden;">
                    <g:pageProperty name="page.panelIzquierdo"/>
                </div>
                <div id="pCentral" class="tamano-padre col">
                    <g:pageProperty name="page.panelCentral"/>
                </div>
                <div id="pDerecho" class="tamano-padre col-auto" style="visibility: hidden;">
                    <g:pageProperty name="page.panelDerecho"/>
                </div>
            </div>
        </div>
        <g:javascript>
            function mostrarPanelIzquierdo() {
                $("#pIzquierdo").css("visibility", "visible");
                $("#pIzquierdo").css("width", "auto");
            }

            function ocultarPanelIzquierdo() {
                $("#pIzquierdo").css("visibility", "hidden");
                $("#pIzquierdo").css("width", 0);
            }

            function mostrarPanelDerecho() {
                $("#pDerecho").css("visibility", "visible");
                $("#pDerecho").css("width", "auto");
            }

            function ocultarPanelDerecho() {
                $("#pDerecho").css("visibility", "hidden");
                $("#pDerecho").css("width", 0);
            }
        </g:javascript>
    </body>
</g:applyLayout>