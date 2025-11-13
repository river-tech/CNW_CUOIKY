<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm sinh viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jspf"/>
<c:url var="studentsUrl" value="/students"/>
<main class="container">
    <section class="card">
        <h2>Thêm sinh viên</h2>
        <form method="post" action="${studentsUrl}">
            <input type="hidden" name="action" value="create">
            <div class="form-field">
                <label for="masv">Mã sinh viên</label>
                <input id="masv" type="text" name="masv" required>
            </div>
            <div class="form-field">
                <label for="hoten">Họ và tên</label>
                <input id="hoten" type="text" name="hoten" required>
            </div>
            <div class="form-field">
                <label for="gioitinh">Giới tính</label>
                <select id="gioitinh" name="gioitinh">
                    <option value="Nam">Nam</option>
                    <option value="Nu">Nữ</option>
                </select>
            </div>
            <div class="form-field">
                <label for="khoa">Khoa</label>
                <input id="khoa" type="text" name="khoa" required>
            </div>
            <button type="submit">Thêm</button>
        </form>
    </section>
</main>
</body>
</html>
