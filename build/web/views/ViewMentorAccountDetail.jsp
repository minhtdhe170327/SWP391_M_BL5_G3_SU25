<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Mentor Account - Admin</title>
    <link href="../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Mentor Account Detail</h2>

    <div class="mb-3">
        <label class="form-label">ID</label>
        <input type="text" class="form-control" value="${mentor.id}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" class="form-control" value="${mentor.accountname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="text" class="form-control" value="${mentor.email}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Role</label>
        <input type="text" class="form-control" value="${mentor.rolename}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">First Name</label>
        <input type="text" class="form-control" value="${mentor.firstname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Last Name</label>
        <input type="text" class="form-control" value="${mentor.lastname}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Address</label>
        <input type="text" class="form-control" value="${mentor.address}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Phone</label>
        <input type="text" class="form-control" value="${mentor.phone}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Birthday</label>
        <input type="text" class="form-control" value="${mentor.birthday}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Sex</label>
        <input type="text" class="form-control" value="${mentor.sex}" disabled>
    </div>

    <div class="mb-3">
        <label class="form-label">Avatar</label>
        <c:if test="${not empty mentor.avatar}">
            <img src="${mentor.avatar}" alt="Avatar" style="max-width:150px;"/>
        </c:if>
    </div>

    <div class="mb-3">
        <label class="form-label">Introduce</label>
        <textarea class="form-control" rows="3" disabled>${mentor.introduce}</textarea>
    </div>

    <div class="mb-3">
        <label class="form-label">Experience</label>
        <textarea class="form-control" rows="3" disabled>${mentor.experience}</textarea>
    </div>

    <div class="mb-3">
        <label class="form-label">Skill</label>
        <input type="text" class="form-control" value="${mentor.skill}" disabled>
    </div>

    <a href="MentorAccountList" class="btn btn-secondary">Back to List</a>
</div>

</body>
</html>
