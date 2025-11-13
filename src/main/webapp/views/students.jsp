<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="fragments/header.jspf"/>
<c:url var="studentsUrl" value="/students"/>
<main class="grid">
    <!-- <section class="card">
        <h2>Tìm kiếm sinh viên</h2>
        <form class="inline-form" method="get" action="${studentsUrl}">
            <div class="form-field">
                <label for="keyword">Họ tên</label>
                <input id="keyword" type="text" name="keyword" placeholder="Nhập họ tên sinh viên" value="${param.keyword}">
            </div>
            <div class="form-field">
                <label for="faculty">Khoa</label>
                <input id="faculty" type="text" name="faculty" placeholder="Nhập khoa" value="${param.faculty}">
            </div>
            <button type="submit">Tìm</button>
        </form>
    </section> -->
    <section class="card wide">
        <div class="list-header">
            <h2>Danh sách sinh viên</h2>
            <div class="filter">
                <form method="get" action="${studentsUrl}">
                    <label for="filterFaculty">Lọc theo khoa</label>
                    <div class="filter-controls">
                        <input id="filterFaculty" type="text" name="faculty" placeholder="VD: CNTT" value="${param.faculty}">
                        <button type="submit">Lọc</button>
                    </div>
                </form>
            </div>
            <button onclick="window.location.href='add'">Thêm sinh viên</button>
        </div>
        <table>
            <thead>
            <tr>
                <th>Mã SV</th>
                <th>Họ tên</th>
                <th>Giới tính</th>
                <th>Khoa</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="stu" items="${students}">
                <tr>
                    <td>
                        <form id="stuForm${stu.id}" method="post" action="${studentsUrl}" class="hidden-form">
                            <input type="hidden" name="id" value="${stu.id}">
                        </form>
                        <input type="text" name="masv" value="${stu.masv}" required form="stuForm${stu.id}">
                    </td>
                    <td>
                        <input type="text" name="hoten" value="${stu.hoten}" required form="stuForm${stu.id}">
                    </td>
                    <td>
                        <select name="gioitinh" form="stuForm${stu.id}">
                            <option value="Nam" <c:if test="${stu.gioitinh eq 'Nam'}">selected</c:if>>Nam</option>
                            <option value="Nu" <c:if test="${stu.gioitinh eq 'Nu'}">selected</c:if>>Nữ</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="khoa" value="${stu.khoa}" required form="stuForm${stu.id}">
                    </td>
                    <td>
                        <button name="action" value="update" form="stuForm${stu.id}">Cập nhật</button>
                        <button name="action" value="delete" class="danger"
                                onclick="return confirm('Xóa sinh viên này?');"
                                form="stuForm${stu.id}">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>
