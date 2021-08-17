<%-- 
    Document   : Inicio
    Created on : 14 ago. 2021, 22:59:16
    Author     : luis
--%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- CSS -->
        <link href="assets/css/margenes.css" rel="stylesheet" type="text/css"/>
        <title>Muebles S.A.</title>
    </head>
    <body>
        <jsp:include page="JSP/Utiles/Navegador.jsp"></jsp:include>
            <div class="card-deck text-center card-produts mt-5">
                <div class="card mb-4 shadow-sm compra-inicio">
                    <div class="card-header">

                    </div>
                    <h1>Descubre nuestros productos</h1>
                    <div class="card-body">
                        <img class="mb-4" src="assets/imagenes/fabrica.jpg" alt="" width="500" height="400">
                        <ul class="list-unstyled mt-3 mb-4">

                        </ul>
                        <a href="${pageContext.request.contextPath}/ControladorProductos?tarea=mostrar"
                       class="btn btn-secondarybtn btn-lg btn-block btn-outline-dark">
                        <i class="fas fa-angle-double-right"></i> Ver productos
                    </a>
                </div>
            </div>
        </div>
        <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    </body>
</html>