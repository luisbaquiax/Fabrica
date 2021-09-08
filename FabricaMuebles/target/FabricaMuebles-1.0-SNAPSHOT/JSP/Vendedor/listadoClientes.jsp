<%-- 
    Document   : listadoClientes
    Created on : 3 sept. 2021, 13:00:36
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
        <!-- personalizados CSS -->
        <link href="../../assets/css/general.css" rel="stylesheet" type="text/css"/>
        <!-- icons -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Listado clientes</title>
    </head>

    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Listado de clientes</h4>
                                </div>
                                <table class="table table-striped">

                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>NIT</th>
                                            <th>NOMBRE</th>
                                            <th>Dirección</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="cliente" items="${clientes}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> <!--${status.count} puede ser esto, o el ID del cliente--> 
                                            <td>${cliente.nit}</td>
                                            <td>${cliente.nombre}</td>
                                            <td>${cliente.direccion}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/controladorTienda?tarea=verCompras&nit=${cliente.nit}"
                                                   class="btn btn-primary">
                                                    <i class="fas fa-arrow-alt-circle-down mr-3"></i>Ver compras
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/controladorTienda?tarea=verDevolucion&nit=${cliente.nit}"
                                                   class="btn btn-warning">
                                                    <i class="fas fa-arrow-alt-circle-down mr-3"></i>Ver devoluciones
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
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>