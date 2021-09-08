<%-- 
    Document   : Productos
    Created on : 15 ago. 2021, 0:25:56
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

        <jsp:include page="../../JSP/Utiles/Navegador.jsp"></jsp:include>
            <div class="container-fluider">
                <div class="row">
                    <form>

                    </form>
                    <div class="col-md-9 pt-5">
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
                                    <a href="${pageContext.request.contextPath}/ControladorVenta?tarea=solicitarCompra&idEnsamblaje=${mueble.identificador}&mueble=${mueble.nombre}"
                                       class="btn btn-secondarybtn btn-lg btn-block btn-outline-primary">Comprar
                                    </a>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
                <div class="col-md-3 pt-5">
                    <div class="card mb-5">
                        <div class="card-header">
                            <h2 class="text-center">Ir al carrito</h2>
                        </div> 
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/ControladorVenta?tarea=solicitarCompra&mueble=${mueble.nombre}&s1_t1"
                               class="btn btn-secondarybtn btn-lg btn-block btn-outline-primary"> Comprar
                            </a>
                        </div> 
                    </div> 
                    <form action="${pageContext.request.contextPath}/ControladorVenta?tarea=devolucion"
                          method="POST">
                        <div class="card border-info mb-3">
                            <div class="card-header"><h5 class="card-title text-center">Devolución</h5></div>
                            <div class="card-body text-info">
                                <label for="factura" class="form-control"><h5 class="card-title text-center">Ingrese:</h5></label>
                                <input class="form-control text-center" type="number" placeholder="No. de Factura" required name="factura" min="1">
                            </div>
                            <div class="card-footer">
                                <button class="btn btn-outline-primary w-100 border-info" type="submit">Ir</button>
                            </div>
                        </div>
                    </form>
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
