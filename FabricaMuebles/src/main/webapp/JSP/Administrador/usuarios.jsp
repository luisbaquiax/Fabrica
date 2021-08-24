<%-- 
    Document   : usuarios
    Created on : 23 ago. 2021, 1:37:26
    Author     : luis
--%>

<!doctype html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- ICONS -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>

        <title>Usuarios</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h1 class="h1 text-center">Usuarios</h1>
                                <a href="#"
                                   data-toggle="modal" data-target="#creaUsuarioModal"
                                   class="btn btn-primary mb-1 w-100">
                                    <i class="fas fa-user-plus"></i> Agregar usuario
                                </a>
                            </div>
                            <div class="card-body">
                                `   <table class="table table-striped">
                                    <thead class="thead-dark">
                                        <tr class="text-center">
                                            <th>#</th>
                                            <th>Identificador</th>
                                            <th>Tipo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${usuarios}" var="usuario" varStatus="contador"> 
                                        <tr class="text-center">
                                            <td>${contador.count}</td>
                                            <td>${usuario.nombre}</td>
                                            <td>${usuario.tipo}</td>
                                        </tr>

                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="crearUsuario.jsp" ></jsp:include>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>
