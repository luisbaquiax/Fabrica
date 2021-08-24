<%-- 
    Document   : crearUsuario
    Created on : 23 ago. 2021, 2:23:36
    Author     : luis
--%>

<div class="modal fade" id="creaUsuarioModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title"><i class="fas fa-user-plus pr-3"></i>Agregar usuario</h5> 
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/controladorAdmin?tarea=agregar"
                  method="POST" class="was-validated">
                <!--class="was-validated" (sirve para validar los campos)-->
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name">Nombre-identificador</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="pass">Contraseña:</label>
                        <input type="text" class="form-control" name="pass" required>
                    </div>
                    <div class="form-group">
                        <label for="tipo">Nombre-identificador</label>
                        <select class="custom-select custom-select-lg mb-3" name="tipo">
                            <option value="1">Área de fábrica</option>
                            <option value="2">Área de venta</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-warning" type="reset">Borrar datos</button>
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>    
            </form>
        </div>
    </div>
</div>

