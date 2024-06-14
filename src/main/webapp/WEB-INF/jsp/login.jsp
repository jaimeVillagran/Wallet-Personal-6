<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" crossorigin="" href="${pageContext.request.contextPath}/styles.css" type="text/css">
</head>
    <body>
        <header class="row mt-5">
            <h1>Login</h1>
        </header>
            <div class="container-main row justify-content-center">
                <div class="col-md-6">
                    <h2 class="text-center mt-5">Ingresa tus credenciales para acceder a tu cuenta.</h2>
                </div>
           </div>
           <c:if test="${not empty error}">
           <p style="color:red;">${error}</p>
           </c:if>
           <form action="<c:url value='/login'/>" method="post">
           <div class="mb-3">
           <label for="email">Email:</label>
           <input type="email" id="email" name="email" required>
           </div>
           <div class="mb-3">
           <label for="password">Password:</label>
           <input type="password" id="password" name="password" required>
           </div>
           <button type="submit">Login</button>
           <a href="<c:url value='/index' />" class="btn btn-secondary">Back to Home</a>
             </form>
    </body>
    </html>
