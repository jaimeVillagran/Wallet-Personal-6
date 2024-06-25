<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                   rel="stylesheet"
                   integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                   crossorigin="anonymous"/>
        <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
</head>
    <body class="d-flex flex-column min-vh-100">
        <header class="row mt-5">
            <h1 class= "text-center">Login</h1>
        </header>
        <div class="container-main row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center mt-5">Ingresa tus credenciales para acceder a tu cuenta.</h2>
                <div class="row justify-content-center mt-3">
                    <form action="<c:url value='/login'/>" method="post">
                    <c:if test="${param.error}">
                        <div class="alert alert-danger">
                            Invalid username or password.
                        </div>
                    </c:if>
                        <div class="mb-2">
                           <label for="email">Email:</label>
                           <input type="email" id="email" name="email" required>
                        </div>
                        <div class="mb-2">
                           <label for="password">Password:</label>
                           <input type="password" id="password" name="password" required>
                        </div>
                            <button class="btn btn-success m-2">Ingresar</button>
                        <div class="btn btn-success m-2">
                            <a href="<c:url value='/index' />">Volver Inicio</a>
                        </div>
                            <c:if test="${not empty error}">
                                <p style="color:red;">${error}</p>
                            </c:if>
                    </form>
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
