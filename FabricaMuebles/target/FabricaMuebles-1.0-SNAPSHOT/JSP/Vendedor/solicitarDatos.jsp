<%-- 
    Document   : solicitarDatos
    Created on : 20 ago. 2021, 1:08:46
    Author     : luis
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- para dar formato el texto-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- SELECCIONAMOS LA LOCALIDAD -->
<fmt:setLocale value="es_GT"/>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- CSS -->
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <title>Solicitando datos del cliente</title>
    </head>
    <body>
        <header id="main-header" class="py-2 bg-info text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="text-center"><i class="fas fa-cog"></i>Solicitar datos y confirmar compra</h1>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="card w-50 margin-auto pt-1">
                <div class="card-header">

                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/ControladorVenta?tarea=registrarCliente&cajero=${cajero}"
                          method="POST" class="was-validated">
                        <input type="hidden" name="mueble" value="${mueble}"> 
                        <input type="hidden" name="idEnsamblaje" value="${idEnsamblaje}"> 
                        <!--class="was-validated" (sirve para validar los campos)-->
                        <div class="form-group">
                            <label for="nit">Su NIT: ${nit}</label>
                            <input type="hidden" name="nit" value="${nit}">
                        </div>
                        <div class="form-group">
                            <label for="nombre">Ingrese su nombre:</label>
                            <input type="text" class="form-control" name="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="direccion">Ingrese su direccion:</label>
                            <input type="text" class="form-control" name="direccion" 
                                   required placeholder="Ciudad o (Departamento, municipio, direccion completa)">
                        </div>
                        <button class="btn bg-info text-white" type="submit">Guardar y realizar la compra</button>
                        <button class="btn bg-info text-white" type="reset">Borrar datos</button>
                    </form>
                </div>
                <div class="card-footer text-center text-muted">
                    <a href="../../index.jsp"
                       class="btn btn-danger">
                        <i class="fas fa-angle-double-right"></i> Cancelar
                    </a>
                </div>
            </div>
        </div>

    </div>
    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>