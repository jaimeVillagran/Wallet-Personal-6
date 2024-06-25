<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                   rel="stylesheet"
                   integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                   crossorigin="anonymous"/>
             <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
</head>
<body class="d-flex flex-column min-vh-100">
    <header class="row mt-5">
    <h1 class="test-centar">Create a New Transaction</h1>
    <form action="${pageContext.request.contextPath}/transaction/new" method="post">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <label for="type">Type:</label>
        <select id="type" name="type" required>
            <c:forEach var="transactionType" items="${transactionTypes}">
                <option value="${transactionType}">${transactionType}</option>
            </c:forEach>
        </select>
        <br>
        <div id="recipientEmailDiv" style="display: none;">
            <label for="recipientEmail">Recipient Email:</label>
            <input type="email" id="recipientEmail" name="recipientEmail">
        </div>
        <br>
        <button type="submit">Create Transaction</button>
    </form>
    <script>
        document.getElementById('type').addEventListener('change', function () {
            var recipientEmailDiv = document.getElementById('recipientEmailDiv');
            if (this.value === 'TRANSFER') {
                recipientEmailDiv.style.display = 'block';
            } else {
                recipientEmailDiv.style.display = 'none';
            }
        });
    </script>
        <footer class="footer mt-auto py-3">
            <div class="container text-center">
                <p class="text-center mt-3 mb-0">&copy; 2024 My Wallet App. Jaime Villagran - Todos los derechos reservados.</p>
            </div>
        </footer>
    </body>
</html>
