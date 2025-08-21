<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Mentee Account - Admin</title>
    <link href="../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Mentee Account Detail</h2>

    <c:if test="${error != null}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Chỉ hiển thị thông tin, không cho sửa -->
    <div class="mb-3">
        <label class="form-label">ID</label>
        <input type="text" class="form-control" value="${mentee.id}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" class="form-control" value="${mentee.accountname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="text" class="form-control" value="${mentee.email}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Role</label>
        <input type="text" class="form-control" value="${mentee.rolename}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">First Name</label>
        <input type="text" class="form-control" value="${mentee.firstname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Last Name</label>
        <input type="text" class="form-control" value="${mentee.lastname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Address</label>
        <input type="text" class="form-control" value="${mentee.address}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Phone</label>
        <input type="text" class="form-control" value="${mentee.phone}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Birthday</label>
        <input type="text" class="form-control" value="${mentee.birthday}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Sex</label>
        <input type="text" class="form-control" value="${mentee.sex}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Introduce</label>
        <textarea class="form-control" rows="4" disabled>${mentee.introduce}</textarea>
    </div>

    <div class="mb-3">
        <label class="form-label">Avatar</label><br>
        <img src="../uploads/${mentee.avatar}" width="150" class="img-thumbnail"/>
    </div>

    <a href="${pageContext.request.contextPath}/MenteeAccountList" class="btn btn-secondary">Back</a>
</div>
</body>
</html>
