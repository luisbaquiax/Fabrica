<%-- 
    Document   : AgregarPieza
    Created on : 17 ago. 2021, 0:40:08
    Author     : luis
--%>

<div class="modal fade" id="agregarPiezaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-warning text-white">
                <h5 class="modal-title"><i class="fas fa-screwdriver pr-3"></i> Agregar Pieza</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/FabricaControlador?tarea=agregar"
                  method="POST" class="was-validated">
                <!--class="was-validated" (sirve para validar los campos)-->
                <div class="modal-body">
                    <div class="form-group">
                        <label for="tipo">Tipo de pieza:</label>
                        <input type="text" class="form-control" name="tipo" required>
                    </div>
                    <div class="form-group">
                        <label for="costo">Costo (Q):</label>
                        <input type="number" class="form-control" name="costo" required min="1" step="0.01">
                    </div>
                    <div class="form-group">
                        <label for="cantidad">Cantidad de existentes:</label>
                        <input type="number" class="form-control" name="cantidad" required min="1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="reset">Borrar datos</button>
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>
