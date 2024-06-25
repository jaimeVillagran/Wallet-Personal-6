<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Main Menu</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                       rel="stylesheet"
                       integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                       crossorigin="anonymous"/>
         <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
    </head>
    <body class="d-flex flex-column min-vh-100">
        <header class="row mt-4">
            <h1 class="text-center">Panel Transacciones</h1>
        </header>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <h2 class="text-center mt-2">Bienvenido, ${user.firstname} ${user.lastname}</h2>
                    <h3 class="text-center mt-2">Saldo Actual: $<span id="balance">${user.balance}</span></h3>

                    <h2 class="text-center mt-4">Panel de Transacciones</h2>
                     <p class="text-center mt-4">Selecciona una opción para gestionar tus fondos.</p>
                        <div class="row justify-content-center">
                            <a href="<c:url value='/deposit' />" class="btn btn-success m-1">Depositar</a>
                            <a href="<c:url value='/withdraw' />" class="btn btn-success m-1">Retirar</a>
                            <a href="<c:url value='/transfer' />" class="btn btn-success m-1">Transferir</a>
                            <a href="<c:url value='/transaction?user_id=${user.userId}' />" class="btn btn-success m-2">Ver Saldo</a>
                            <a href="<c:url value='/logout' />" class="btn btn-danger m-1">Cerrar Sesión</a>

                        </div>
                </div>
            </div>
        </div>
        <footer class="footer mt-auto py-3">
            <div class="container text-center">
                <p class="text-center mt-3 mb-0">&copy; 2024 My Wallet App. Jaime Villagran - Todos los derechos reservados.</p>
            </div>
        </footer>
        <script>
                        // JavaScript para formatear el balance
                        document.addEventListener("DOMContentLoaded", function() {
                            const balanceElement = document.getElementById('balance');
                            const balanceValue = parseFloat(balanceElement.textContent);

                            if (!isNaN(balanceValue)) {
                                // Formatear el balance
                                const formattedBalance = balanceValue.toLocaleString('es-ES', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
                                balanceElement.textContent = formattedBalance;
                            }
                        });
                    </script>
    </body>
</html>
