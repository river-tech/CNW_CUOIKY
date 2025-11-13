<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="auth-page">
<div class="auth-card">
    <h2>Create Account</h2>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <label>Username</label>
        <input type="text" name="username" required>
        <label>Password</label>
        <input type="password" name="password" required>
        <label>Last name</label>
        <input type="text" name="lastname" required>
        <label>Role</label>
        <select name="role">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>
        <button type="submit">Register</button>
    </form>
    <p>Already registered? <a href="${pageContext.request.contextPath}/login">Back to Login</a></p>
</div>
</body>
</html>
