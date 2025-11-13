<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jspf"/>
<c:url var="searchUrl" value="/search"/>
<main class="container">
    <section class="card">
        <h2>Search Users</h2>
        <form class="inline-form" method="get" action="${searchUrl}">
            <input type="text" name="keyword" placeholder="Lastname or Username" value="${keyword}">
            <button type="submit">Search</button>
        </form>
    </section>
    <section class="card">
        <h3>Results</h3>
        <c:choose>
            <c:when test="${empty users}">
                <p>No Result is matched!</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Lastname</th>
                        <th>Role</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>
                                ${user.id}
                                <form id="userForm${user.id}" method="post" action="${searchUrl}" class="hidden-form">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="keyword" value="${keyword}">
                                </form>
                            </td>
                            <td>${user.username}</td>
                            <td>
                                <input type="text" name="lastname" value="${user.lastname}" form="userForm${user.id}">
                            </td>
                            <td>
                                <select name="role" form="userForm${user.id}">
                                    <option value="user" <c:if test="${user.role eq 'user'}">selected</c:if>>User</option>
                                    <option value="admin" <c:if test="${user.role eq 'admin'}">selected</c:if>>Admin</option>
                                </select>
                            </td>
                            <td>
                                <button name="action" value="update" form="userForm${user.id}">Update</button>
                                <button name="action" value="delete" class="danger"
                                        onclick="return confirm('Delete user?');"
                                        form="userForm${user.id}">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </section>
</main>
</body>
</html>
