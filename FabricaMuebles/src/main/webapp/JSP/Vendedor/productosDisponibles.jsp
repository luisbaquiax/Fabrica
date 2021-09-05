<%-- 
    Document   : productosDisponibles
    Created on : 3 sept. 2021, 2:49:34
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
        <link href="../../assets/css/Productos.css" rel="stylesheet" type="text/css"/>
        <link href="../../assets/css/CardProductos.css" rel="stylesheet" type="text/css"/>
        <link href="../../assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <title>Productos</title>
    </head>
    <body>

        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container-fluider">
                <div class="row">
                    <div class="col-md-12 pt-5">
                        <h1 class="text-center">Productos disponibles</h1>
                    <c:forEach var="mueble" items="${muebles}">

                        <div class="card-deck text-center card-produts mr-2 ml-2">
                            <div class="card mb-4 shadow-sm">
                                <div class="card-header">
                                    <h4 class="my-0 font-weight-normal">${mueble.nombre}</h4>
                                </div>
                                <div class="card-body">
                                    <h1 class="card-title pricing-card-title">${mueble.nombre}<small class="text-muted"><!-- puede ir alguna signa aquí --></small></h1>
                                    <img class="mb-4 margin-auto " src="../../assets/imagenes/calcular-medida-mesas-comedor.jpg" alt="" width="172" height="172">
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li>Precio:</li>
                                        <li><fmt:formatNumber value="${mueble.precio}" type="currency"/></li>
                                        <li>Identificador:</li>
                                        <li>${mueble.identificador}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>

            </div>
            <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>