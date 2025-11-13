<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="auth-page">
<div class="auth-card">
    <h2>Login</h2>
    <c:if test="${not empty error}">
        <div class="alert">${error}</div>
        <a class="retry" href="${pageContext.request.contextPath}/login">Try Again</a>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label>Username</label>
        <input type="text" name="username" required>
        <label>Password</label>
        <input type="password" name="password" required>
        <button type="submit">Sign In</button>
    </form>
    <p>New user? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
</div>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
