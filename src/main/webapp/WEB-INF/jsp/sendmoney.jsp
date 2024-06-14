<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Money</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <h1>Send Money</h1>
    <form action="${pageContext.request.contextPath}/sendmoney" method="post">
        <label for="recipientEmail">Recipient Email:</label>
        <input type="email" id="recipientEmail" name="recipientEmail" required>
        <br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <button type="submit">Send Money</button>
    </form>
</body>
</html>

