<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty sessionScope.account || sessionScope.account.roleid != 3}">
    <c:redirect url="Login" />
</c:if>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Mentor Account - Admin</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f4f7f6; }
        .profile-container { max-width: 900px; margin-top: 80px; margin-bottom: 50px; }
        .profile-card { background-color: #fff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        .profile-body { padding: 30px; }
        .profile-body h4 { color: #0d47a1; border-bottom: 2px solid #eee; padding-bottom: 10px; margin-bottom: 20px; }
        .info-item { display: flex; align-items: flex-start; margin-bottom: 15px; }
        .info-item strong { min-width: 150px; color: #555; margin-top: 8px; }
        .form-control, .form-select, textarea { flex: 1; }
    </style>
</head>
<body>
    <jsp:include page="../guest/Header.jsp" />

    <main class="container profile-container">
        <div class="profile-card">
            <form action="${contextPath}/AdminEditMentorAccount" method="post" class="profile-body">

                <!-- hidden id -->
                <input type="hidden" name="accountId" value="${editAccount.id}"/>

                <h4>Edit Mentor Account</h4>

                <div class="info-item">
                    <strong>Account Name:</strong>
                    <input type="text" name="accountname" value="${editAccount.accountname}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>First Name:</strong>
                    <input type="text" name="firstname" value="${mentor.firstname}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>Last Name:</strong>
                    <input type="text" name="lastname" value="${mentor.lastname}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>Email:</strong>
                    <input type="email" name="email" value="${editAccount.email}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>Password:</strong>
                    <div class="input-group">
                        <input type="password" id="passwordField" name="password" value="" placeholder="Nhập mật khẩu mới nếu muốn đổi" class="form-control"/>
                        <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('passwordField', this)">Show</button>
                    </div>
                </div>

                <div class="info-item">
                    <strong>Phone:</strong>
                    <input type="text" name="phone" value="${mentor.phone}" class="form-control" pattern="0[0-9]{9}"/>
                </div>

                <div class="info-item">
                    <strong>Address:</strong>
                    <input type="text" name="address" value="${mentor.address}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>Birthday:</strong>
                    <fmt:formatDate value="${mentor.birthday}" pattern="yyyy-MM-dd" var="dob"/>
                    <input type="date" name="birthday" value="${dob}" class="form-control"/>
                </div>

                <div class="info-item">
                    <strong>Gender:</strong>
                    <select name="sex" class="form-select">
                        <option value="M" <c:if test="${mentor.sex eq 'M'}">selected</c:if>>Male</option>
                        <option value="F" <c:if test="${mentor.sex eq 'F'}">selected</c:if>>Female</option>
                    </select>
                </div>

                <div class="info-item">
                    <strong>Introduction:</strong>
                    <textarea name="introduce" class="form-control">${mentor.introduce}</textarea>
                </div>

                <div class="info-item">
                    <strong>Achievement:</strong>
                    <textarea name="achievement" class="form-control">${mentor.achievement}</textarea>
                </div>

                <div class="info-item">
                    <strong>Cost / Hire:</strong>
                    <input type="number" name="cost" value="${mentor.costHire}" class="form-control"/>
                </div>

                <div class="mt-4 text-center">
                    <button type="submit" class="btn btn-warning">Save</button>
                    <a href="ViewMentorAccount" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="../guest/Footer.jsp" />

    <script>
        function togglePassword(fieldId, btn) {
            const field = document.getElementById(fieldId);
            if (field.type === "password") {
                field.type = "text";
                btn.textContent = "Hide";
            } else {
                field.type = "password";
                btn.textContent = "Show";
            }
        }
    </script>
</body>
</html>
