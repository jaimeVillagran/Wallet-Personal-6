<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposito</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                   rel="stylesheet"
                   integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                   crossorigin="anonymous"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="d-flex flex-column min-vh-100">
<header class="row mt-5">
                       <h1 class= "text-center">Wallet Profesional</h1>
                   </header>
        <div class="container">
            <div class="container-main row justify-content-center">
                <div class="col-md-6">
                <p class="text-center mt-5">Depositos Confiables.</p>
                    <h2 class="text-center mt-5">Depositar Fondos</h2>
                     <h3>Saldo Actual: ${user.balance}</h3>
                      <c:if test="${not empty error}">
                                     <div class="alert alert-danger">${error}</div>
                                 </c:if>
                                 <c:if test="${not empty message}">
                                     <div class="alert alert-success">${message}</div>
                                 </c:if>
    <h1>Deposit Funds</h1>
    <form action="<c:url value='/deposit' />" method="post">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <button type="submit" class="btn btn-success">Depositar</button>
    </form>
    <a href= "<c:url value='/dashboard' />" class="btn btn-secondary mt-3">Volver al Dashboard</a>
    <footer class="footer mt-auto py-3">
        <div class="container text-center">
            <p class="text-center mt-3 mb-0">&copy; 2024 My Wallet App. Jaime Villagran - Todos los derechos reservados.</p>
        </div>
    </footer>
</body>
</html>
