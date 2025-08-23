<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Mentee Account - Admin</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
      .form-container {
        max-width: 600px;
        margin: 0 auto;
        background: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.08);
      }
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<div class="container mt-5">
  <h2 class="mb-4">Add Mentee Account</h2>

  <c:if test="${error != null}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <form action="AdminAddMenteeAccount" method="post" class="form-container">
    <!-- Username -->
    <div class="mb-3">
      <label class="form-label">Username <span class="text-danger">*</span></label>
      <input name="accountname" type="text" class="form-control" required
             value="${param.accountname}" />
    </div>

    <!-- Email -->
    <div class="mb-3">
      <label class="form-label">Email <span class="text-danger">*</span></label>
      <input name="email" type="email" class="form-control" required
             value="${param.email}" />
    </div>

    <!-- Password -->
    <div class="mb-3">
      <label class="form-label">Password <span class="text-danger">*</span></label>
      <input name="password" type="password" class="form-control" required minlength="6" />
    </div>

    <!-- Firstname -->
    <div class="mb-3">
      <label class="form-label">First Name <span class="text-danger">*</span></label>
      <input name="firstname" type="text" class="form-control" required
             value="${param.firstname}" />
    </div>

    <!-- Lastname -->
    <div class="mb-3">
      <label class="form-label">Last Name <span class="text-danger">*</span></label>
      <input name="lastname" type="text" class="form-control" required
             value="${param.lastname}" />
    </div>

    <!-- Address -->
    <div class="mb-3">
      <label class="form-label">Address</label>
      <input name="address" type="text" class="form-control"
             value="${param.address}" />
    </div>

    <!-- Phone -->
    <div class="mb-3">
      <label class="form-label">Phone</label>
      <input name="phone" type="text" class="form-control"
             pattern="[0-9]{10,}" title="Enter valid phone number"
             value="${param.phone}" />
    </div>

    <!-- Birthday -->
    <div class="mb-3">
      <label class="form-label">Birthday</label>
      <input name="birthday" type="date" class="form-control"
             value="${param.birthday}" />
    </div>

    <!-- Sex -->
    <div class="mb-3">
      <label class="form-label">Sex</label>
      <select name="sex" class="form-select">
        <option value="M" ${param.sex == 'M' ? 'selected' : ''}>Male</option>
        <option value="F" ${param.sex == 'F' ? 'selected' : ''}>Female</option>
      </select>
    </div>

    <!-- Avatar -->
    <div class="mb-3">
      <label class="form-label">Avatar</label>
      <input name="avatar" type="text" class="form-control"
             placeholder="Enter avatar file name or URL"
             value="${param.avatar}" />
    </div>

    <!-- Introduce -->
    <div class="mb-3">
      <label class="form-label">Introduce</label>
      <textarea name="introduce" class="form-control">${param.introduce}</textarea>
    </div>

    <!-- Hidden roleid -->
    <input type="hidden" name="roleid" value="1"/>

    <!-- Buttons -->
    <div class="mt-4">
      <button type="submit" class="btn btn-success">Add Mentee Account</button>
      <a href="ViewMenteeAccount" class="btn btn-secondary">Cancel</a>
    </div>
  </form>
</div>
</body>
</html>
