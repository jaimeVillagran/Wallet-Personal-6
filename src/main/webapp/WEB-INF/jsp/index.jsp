<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
    <<head>
         <meta charset="UTF-8">
         <title>Wallet Profesional</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
               rel="stylesheet"
               integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
               crossorigin="anonymous"/>
               <link rel="stylesheet" cross origin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <header class="row mt-5">
            <h1 class= "text-center">Bienvenido a Wallet Profesional</h1>
        </header>
            <div class="container mt-5">
                <div class="container-main row justify-content-center">
                    <div class="col-md-5">
                        <h3 class="text-center mt-5">Tu aplicación confiable para gestionar transacciones financieras de manera segura y eficiente.</h3>
                        <p class="text-center mt-5">Inicia sesión o regístrate para comenzar a gestionar tus fondos.</p>
                            <div class="btn btn-success mb-2" >
                                <a href= "<c:url value='/login' />">Iniciar Sesión</a>
                            </div>
                            <div class="btn btn-success mb-2" >
                                <a href="<c:url value='/register' />">Crear Usuario</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="footer mt-auto py-3">
                <div class="container text-center">
                    <p class="text-center mt-3 mb-0">&copy; 2024 My Wallet App. Jaime Villagran - Todos los derechos reservados.</p>
                </div>
            </footer>
    </body>
</html>
