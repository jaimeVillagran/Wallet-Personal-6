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
               <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <header class="row mt-5">
            <h1 class= "text-center">Wallet Profesional</h1>
        </header>
        <div class="container">
            <div class="container-main row justify-content-center">
                <div class="col-md-6">
                    <p class="text-center mt-5">Transferencias Confiables.</p>
                    <h2 class="text-center mt-5">Transferir Fondos</h2>
                     <h3>Saldo Actual: ${user.balance}</h3>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">${message}</div>
                        </c:if>
                    <form action="<c:url value='/transfer' />" method="post">
                        <div class="mb-3">
                            <label for="recipientEmail" class="form-label">Correo Electrónico del Destinatario</label>
                            <select class="form-select" id="recipientEmail" name="recipientEmail" required>
                                <c:forEach var="user" items="${users}">
                                <option value="${user.email}">${user.firstname} ${user.lastname}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="amount" class="form-label">Monto</label>
                            <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
                        </div>
<button type="submit" class="btn btn-success">Transferir</button>
</form>
<a href= "<c:url value='/dashboard' />" class="btn btn-secondary mt-3">Volver al Dashboard</a>
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