<%-- 
    Document   : mostrarEnsamblajes
    Created on : 19 ago. 2021, 0:56:32
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
        <!-- personalizados CSS -->
        <link href="../../assets/css/general.css" rel="stylesheet" type="text/css"/>
        <title>Muebles a ensamblar</title>
    </head>

    <body>
        <jsp:include page="Navegador.jsp"></jsp:include>
            <section class="mt-5 opacity">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header mt-3">
                                    <h4 class="text-center">Muebles ensamblados</h4>
                                </div>
                                <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=fechaAscendente"
                               class="btn btn-warning mb-1">
                                <i class="fas fa-angle-double-up"></i> Ordenar por fecha (ASC)
                            </a>
                            <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=fechaDescendente"
                               class="btn btn-warning mb-1">
                                <i class="fas fa-angle-double-down"></i> Ordenar por fecha (DESC)
                            </a>
                            <table class="table table-striped">

                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Fecha de ensamblaje</th>
                                        <th>Costo</th>
                                        <th>Estado</th>
                                        <th>Mueble</th>
                                        <th>Ensamblado por</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="ensamblaje" items="${ensamblajes}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> <!--${status.count} --> 
                                            <td>${ensamblaje.fecha}</td>
                                            <td> <fmt:formatNumber value="${ensamblaje.costo}" type="currency"/> </td>
                                            <c:if test="${ensamblaje.estado==false}">
                                                <td class="text-danger">No registrado</td>
                                            </c:if>
                                            <c:if test="${ensamblaje.estado==true}">
                                                <td class="text-success">Registrado</td>
                                            </c:if>
                                            <td>${ensamblaje.mueble}</td>
                                            <td>${ensamblaje.usuario}</td>
                                            <td><a href="${pageContext.request.contextPath}/FabricaControlador?tarea=verDetalle&id=${ensamblaje.id}"
                                                   class="btn btn-primary mb-1">
                                                    <i class="fas fa-arrow-alt-circle-down"></i> Ver detalle
                                                </a></td>
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