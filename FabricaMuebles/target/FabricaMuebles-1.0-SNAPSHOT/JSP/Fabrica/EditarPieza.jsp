<%-- 
    Document   : EditarPieza
    Created on : 17 ago. 2021, 1:12:09
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
              integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Editar Pieza</title>
    </head>

    <body>

        <form action="${pageContext.request.contextPath}/FabricaControlador?tarea=actualizarPieza&tipo=${pieza.tipo}"
              method="POST" class="was-validated">
            <!--navegacion-->
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4><i class="fas fa-tools pr-3"></i>Editar Pieza</h4>
                                    <a href="/FabricaMuebles/JSP/Fabrica/ListadoPiezas.jsp"
                                       class="btn btn-warning form-signin">
                                        <i class="fas fa-arrow-circle-left"></i> Regresar
                                    </a>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="tipo">Tipo:</label>
                                        <input type="text" class="form-control" name="tipo" required value="${pieza.tipo}">
                                    </div>
                                    <div class="form-group">
                                        <label for="costo">Costo:</label>
                                        <input type="number" class="form-control" name="costo" required min="0" value="${pieza.costo}" step="0.01">
                                    </div>
                                    <div class="form-group">
                                        <label for="cantidad">Agregar unidades nuevas (existentes = ${pieza.cantidadExistente}):</label>
                                        <input type="number" class="form-control" name="cantidad" required min="0" value="0">
                                    </div>
                                    <div class="form-group">
                                        <label for="cantidad2">Quitar unidades nuevas (existentes = ${pieza.cantidadExistente}):</label>
                                        <input type="number" class="form-control" name="cantidad2" required min="0" value="0">
                                    </div>
                                </div>
                                <div class="card-footer bg-transparent border-success">
                                    <button class="btn btn-primary w-100 mx-auto" type="submit">Guardar cambios</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </form>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

    </body>

</html>


