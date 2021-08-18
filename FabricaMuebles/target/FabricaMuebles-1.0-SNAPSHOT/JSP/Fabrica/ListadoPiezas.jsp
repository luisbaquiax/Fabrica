<%-- 
    Document   : ListadoPiezas
    Created on : 16 ago. 2021, 23:43:57
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
        <title>Listado piezas</title>
    </head>

    <body>
        <jsp:include page="Navegador.jsp"></jsp:include>
            <section class="mt-5">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="text-center">Listado de Piezas</h4>
                                </div>
                                <a href="#"
                                   data-toggle="modal" data-target="#agregarPiezaModal"
                                   class="btn btn-primary mb-1">
                                    <i class="fas fa-plus"></i> Agregar Pieza Nueva
                                </a>
                                <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=ascendente"
                               class="btn btn-warning mb-1">
                                <i class="fas fa-angle-double-up"></i> Ordenar por cantidad de unidades (ASC)
                            </a>
                            <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=descendente"
                               class="btn btn-warning mb-1">
                                <i class="fas fa-angle-double-down"></i> Ordenar por cantidad de unidades (DESC)
                            </a>
                            <table class="table table-striped">

                                <thead class="thead-dark">
                                    <tr>
                                        <th>No</th>
                                        <th>Tipo</th>
                                        <th>Costo</th>
                                        <th>Cantidad de unidades</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="pieza" items="${piezas}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td> <!--${status.count} puede ser esto, o el ID del cliente--> 
                                            <td>${pieza.tipo}</td>
                                            <!-- --> 
                                            <td> <fmt:formatNumber value="${pieza.costo}" type="currency"/> </td>
                                            <td>${pieza.cantidadExistente}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=editar&tipo=${pieza.tipo}"
                                                   class="btn btn-warning">
                                                    <i class="fas fa-tools"></i> Editar
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=eliminarPieza&tipo=${pieza.tipo}"
                                                   class="btn btn-danger">
                                                    <i class="fas fa-trash-alt"></i> Eliminar
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
        <jsp:include page="AgregarPieza.jsp"></jsp:include>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

    </body>

</html>


