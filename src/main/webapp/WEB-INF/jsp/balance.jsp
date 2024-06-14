<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction History</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                           rel="stylesheet"
                           integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                           crossorigin="anonymous"/>
             <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
</head>
<body>
    <h1>Transaction History</h1>
    <h3>Saldo Actual: <c:out value="${balance}"/></h3>
    <table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="transaction" items="${transactions}">
                <tr>
                    <td><c:out value="${transaction.transactionDate}"/></td>
                    <td><c:out value="${transaction.transactionType}"/></td>
                    <td><c:out value="${transaction.amount}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action= "<c:url value='/dashboard' />" method="get">
            <button type="submit" class="btn btn-primary">Volver al Dashboard</button>
        </form>
        <footer class="footer mt-auto py-3">
                    <div class="container text-center">
                        <p class="text-center mt-3 mb-0">&copy; 2024 My Wallet App. Jaime Villagran - Todos los derechos reservados.</p>
                    </div>
                </footer>
</body>
</html>

