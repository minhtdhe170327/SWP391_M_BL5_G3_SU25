<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Mentee Account - Admin</title>
    <link href="../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; }
        .profile-container { max-width: 900px; margin-top: 80px; margin-bottom: 50px; }
        .profile-card { background-color: #fff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        .profile-body { padding: 30px; }
        .profile-body h4 { color: #0d47a1; border-bottom: 2px solid #eee; padding-bottom: 10px; margin-bottom: 20px; }
        .info-item { display: flex; align-items: flex-start; margin-bottom: 15px; }
        .info-item strong { min-width: 150px; color: #555; margin-top: 8px; }
        .form-control, textarea { flex: 1; }
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<main class="container profile-container">
    <div class="profile-card">
        <div class="profile-body">
            <h4>Mentee Account Detail</h4>

            <div class="info-item">
                <strong>ID:</strong>
                <input type="text" class="form-control" value="${mentee.id}" disabled>
            </div>

            <div class="info-item">
                <strong>Username:</strong>
                <input type="text" class="form-control" value="${mentee.accountname}" disabled>
            </div>

            <div class="info-item">
                <strong>Email:</strong>
                <input type="text" class="form-control" value="${mentee.email}" disabled>
            </div>

            <div class="info-item">
                <strong>Role:</strong>
                <input type="text" class="form-control" value="${mentee.rolename}" disabled>
            </div>

            <div class="info-item">
                <strong>First Name:</strong>
                <input type="text" class="form-control" value="${mentee.firstname}" disabled>
            </div>

            <div class="info-item">
                <strong>Last Name:</strong>
                <input type="text" class="form-control" value="${mentee.lastname}" disabled>
            </div>

            <div class="info-item">
                <strong>Address:</strong>
                <input type="text" class="form-control" value="${mentee.address}" disabled>
            </div>

            <div class="info-item">
                <strong>Phone:</strong>
                <input type="text" class="form-control" value="${mentee.phone}" disabled>
            </div>

            <div class="info-item">
                <strong>Birthday:</strong>
                <input type="text" class="form-control" value="<fmt:formatDate value='${mentee.birthday}' pattern='yyyy-MM-dd'/>" disabled>
            </div>

            <div class="info-item">
                <strong>Sex:</strong>
                <input type="text" class="form-control" value="${mentee.sex}" disabled>
            </div>

            <div class="info-item">
                <strong>Introduce:</strong>
                <textarea class="form-control" rows="4" disabled>${mentee.introduce}</textarea>
            </div>

            <div class="mt-4 text-center">
                <a href="javascript:history.back()" class="btn btn-secondary">Back</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../guest/Footer.jsp" />
</body>
</html>
