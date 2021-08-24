<%-- 
    Document   : compra
    Created on : 19 ago. 2021, 23:06:35
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
        <title>Confirmación de la compra</title>
    </head>
    <body>

        <jsp:include page="Cabecero.jsp"></jsp:include>
            <div class="container-fluider">
            <c:forEach var="c" items="${cajeros}" varStatus="contador">

                <div class="card text-center margin-auto ml-4 mt-4">
                    <div class="card-header">
                        <h1 class="h1">Cajero ${contador.count}</h1>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/ControladorVenta?tarea=buscarCliente&mueble=${mueble.nombre}&cajero=${c.nombre}"
                              method="POST">

                            <!--class="was-validated" (sirve para validar los campos)-->
                            <div class="form-group">
                                <label for="nit">Ingrese su NIT:</label>
                                <input type="text" class="form-control" name="nit" required>
                            </div>
                            <button class="btn bg-info text-white" type="submit">Aceptar y realizar la compra</button>
                            <button class="btn bg-info text-white" type="reset">Borrar datos</button>
                        </form>
                    </div>
                    <div class="card-footer text-muted">
                        <a href="../../index.jsp"
                           class="btn btn-danger">
                            <i class="fas fa-angle-double-right"></i> Cancelar
                        </a>
                    </div>
                </div>

            </c:forEach>
        </div>

        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>