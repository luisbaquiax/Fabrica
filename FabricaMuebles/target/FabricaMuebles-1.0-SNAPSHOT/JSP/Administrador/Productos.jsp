<%-- 
    Document   : Productos
    Created on : 15 ago. 2021, 0:25:56
    Author     : luis
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <title>Productos</title>
    </head>
    <body>

        <jsp:include page="../../JSP/Utiles/Navegador.jsp"></jsp:include>


            <div class="container mt-5">

                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="mueble" items="${muebles}" varStatus="status" >
                        <tr>
                            <td>${mueble.nombre}</td>
                            <td>${mueble.precio}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controladorCliente?accion=editar&id=${cliente.id}"
                                   class="btn btn-secondary">
                                    <i class="fas fa-angle-double-right"></i> Editar
                                </a>
                            </td>
                        </tr>
                    <div class="card-deck text-center card-produts mr-2">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                <h4 class="my-0 font-weight-normal">${mueble.nombre}</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">${mueble.nombre}<small class="text-muted">/ mo</small></h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>Precio</li>
                                    <li>${mueble.precio}</li>
                                    <li>Email support</li>
                                    <li>Help center access</li>
                                </ul>
                                <a href="${pageContext.request.contextPath}/controladorCliente?accion=editar&id=${mueble.nombre}"
                                   class="btn btn-secondarybtn btn-lg btn-block btn-outline-primary">
                                    <i class="fas fa-angle-double-right"></i> Comprar
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>

            <div class="card-deck text-center card-produts mr-2">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Free</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">${mueble.nombre}<small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>10 users included</li>
                            <li>2 GB of storage</li>
                            <li>Email support</li>
                            <li>Help center access</li>
                        </ul>
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
                    </div>
                </div>
            </div>

            <footer class="pt-4 my-md-5 pt-md-5 border-top">
                <div class="row">
                    <div class="col-12 col-md">
                        <img class="mb-2" src="" alt="" width="24" height="24">
                        <small class="d-block mb-3 text-muted">&copy; 2017-2021</small>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Features</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Cool stuff</a></li>
                            <li><a class="text-muted" href="#">Random feature</a></li>
                            <li><a class="text-muted" href="#">Team feature</a></li>
                            <li><a class="text-muted" href="#">Stuff for developers</a></li>
                            <li><a class="text-muted" href="#">Another one</a></li>
                            <li><a class="text-muted" href="#">Last time</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Resources</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Resource</a></li>
                            <li><a class="text-muted" href="#">Resource name</a></li>
                            <li><a class="text-muted" href="#">Another resource</a></li>
                            <li><a class="text-muted" href="#">Final resource</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>About</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Team</a></li>
                            <li><a class="text-muted" href="#">Locations</a></li>
                            <li><a class="text-muted" href="#">Privacy</a></li>
                            <li><a class="text-muted" href="#">Terms</a></li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>
