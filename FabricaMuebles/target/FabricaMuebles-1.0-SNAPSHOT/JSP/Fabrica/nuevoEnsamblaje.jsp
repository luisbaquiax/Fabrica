<%-- 
    Document   : nuevoEnsamblaje
    Created on : 18 ago. 2021, 0:31:55
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
        <title>Nuevo ensamblaje</title>
    </head>

    <body>
        <jsp:include page="Navegador.jsp"></jsp:include>
            <section class="mt-5">
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">

                                <div class="card-header mt-4 pt-4">
                                    <h4 class="text-center">Ensamblar mueble: ${mueble}</h4>
                            </div>
                            <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=agregarEnsamble&mueble=${mueble}"
                               class="btn btn-success mb-1">
                                <i class="fas fa-tools"></i> Ensamblar
                            </a>
                            <a href="${pageContext.request.contextPath}/FabricaControlador?tarea=cancelarEnsamble"
                               class="btn btn-danger mb-1">
                                <i class="fas fa-window-close"></i> Cancelar
                            </a>

                            <table class="table table-striped">

                                <thead class="thead-dark">
                                    <tr>
                                        <th>Piezas requeridas</th>
                                        <th>Cantidad de piezas</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="re" items="${requerimientos}" varStatus="status" >
                                        <tr>
                                            <td>${re.pieza}</td>
                                            <td>${re.cantidadPiezas}</td>
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