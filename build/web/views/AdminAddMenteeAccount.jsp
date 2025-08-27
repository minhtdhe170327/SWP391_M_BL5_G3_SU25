<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Add Mentee Account | Admin Dashboard</title>
    <meta name="description" content="Add a new Mentee account to the system. Secure and SEO-friendly Admin Dashboard form."/>
    <meta name="keywords" content="Mentee, Account, Add, Admin, Dashboard"/>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet"/>
    <style>
        body {background-color: #f8f9fa;}
        .form-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding-top: 80px; /* tránh bị che bởi header */
        }
        .form-container {
            width: 100%;
            max-width: 650px;
            background: #fff;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .form-container h2 {
            font-weight: 600;
            margin-bottom: 25px;
            color: #333;
            display: flex;
            align-items: center;
        }
        .form-container h2 i {
            margin-right: 10px;
            color: #17a2b8;
        }
        .form-label {font-weight: 500;}
        .btn-success {
            background-color: #28a745;
            border: none;
            font-weight: 600;
        }
        .btn-success:hover {background-color: #218838;}
        .btn-secondary {font-weight: 500;}
        textarea {resize: vertical;}
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<div class="container form-wrapper">
    <div class="form-container">
        <h2><i class="bi bi-person-plus"></i> Add Mentee Account</h2>

        <c:if test="${error != null}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form action="AdminAddMenteeAccount" method="post">
            <div class="mb-3">
                <label class="form-label">Username <span class="text-danger">*</span></label>
                <input name="accountname" type="text" class="form-control" required value="${param.accountname}"/>
            </div>

            <div class="mb-3">
                <label class="form-label">Email <span class="text-danger">*</span></label>
                <input name="email" type="email" class="form-control" required value="${param.email}"/>
            </div>

            <div class="mb-3">
                <label class="form-label">Password <span class="text-danger">*</span></label>
                <input name="password" type="password" class="form-control" required minlength="6"/>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">First Name <span class="text-danger">*</span></label>
                    <input name="firstname" type="text" class="form-control" required value="${param.firstname}"/>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Last Name <span class="text-danger">*</span></label>
                    <input name="lastname" type="text" class="form-control" required value="${param.lastname}"/>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Address</label>
                <input name="address" type="text" class="form-control" value="${param.address}"/>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Phone</label>
                    <input name="phone" type="text" class="form-control" pattern="[0-9]{10,}" title="Enter valid phone number" value="${param.phone}"/>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Birthday</label>
                    <input name="birthday" type="date" class="form-control" value="${param.birthday}"/>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Sex</label>
                <select name="sex" class="form-select">
                    <option value="M" ${param.sex == 'M' ? 'selected' : ''}>Male</option>
                    <option value="F" ${param.sex == 'F' ? 'selected' : ''}>Female</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Avatar</label>
                <input name="avatar" type="text" class="form-control" placeholder="Enter avatar file name or URL" value="${param.avatar}"/>
            </div>

            <div class="mb-3">
                <label class="form-label">Introduce</label>
                <textarea name="introduce" rows="3" class="form-control">${param.introduce}</textarea>
            </div>

            <input type="hidden" name="roleid" value="1"/>

            <div class="mt-4 d-flex justify-content-between">
                <button type="submit" class="btn btn-success"><i class="bi bi-check2-circle me-2"></i>Add</button>
                <a href="ViewMenteeAccount" class="btn btn-secondary"><i class="bi bi-x-circle me-2"></i>Cancel</a>
            </div>
        </form>
    </div>
</div>

<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
