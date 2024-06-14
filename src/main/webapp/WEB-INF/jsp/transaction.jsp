<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <h1>Create a New Transaction</h1>
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
</body>
</html>
