<%-- 
    Document   : ensamblarMueble
    Created on : 17 ago. 2021, 18:35:28
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
        <!-- CSS -->
        <title>Muebles a ensamblar</title>
    </head>

    <body>
        <jsp:include page="Navegador.jsp"></jsp:include>
            <section class="mt-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header mt-3">
                                    <h4 class="text-center">Listado de Muebles</h4>
                                </div>

                                <table class="table table-striped">

                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Nombre del mueble</th>
                                            <th>Costo</th>
                                            <th>Cantidad de unidades</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="mueble" items="${mubles}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> <!--${status.count} --> 
                                            <td>${mueble.nombre}</td>
                                            <!-- --> 
                                            <td> <fmt:formatNumber value="${mueble.precio}" type="currency"/> </td>
                                            <td class="text-center">${mueble.cantidadExistente}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=ensamblaje&mueble=${mueble.nombre}"
                                                   class="btn btn-warning">
                                                    <i class="fas fa-tools"></i> Ensamblar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

    </body>

</html>