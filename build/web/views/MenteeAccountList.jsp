<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>AccountList - Mentee</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
<c:if test="${sessionScope.account.roleid==3}">
    <jsp:include page="../guest/Header.jsp" />
    <main id="main" style="position: relative">
        <section class="breadcrumbs">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Account List (Mentee Only)</h2>
                    <ol>
                        <li><a href="ViewTop3Mentor">Home</a></li>
                    </ol>
                </div>
            </div>
        </section>

        <div class="d-flex">
            <!-- Search -->
            <form action="SearchAccount" method="post">
                <div class="input-group" style="margin: 10px; padding-right: 500px; padding-left: 100px;">
                    <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                    <button type="submit" class="btn btn-secondary"><i class="bi bi-search"></i></button>
                </div>
            </form>
            <a href="AdminAddMenteeAccount" 
                class="btn btn-success btn-lg d-flex align-items-center justify-content-center"
                style="margin-top: 10px; margin-right: 50px; font-size: 15px; float: right; width: 300px; height: 50px;">
                <i class="bi bi-person-plus me-2"></i> Add Mentee Account
            </a>
        </div>

        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-start align-items-center h-100">
                <table class="table table-striped table-hover" style="margin: 50px; width:93%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>AccountName</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th style="width:120px;">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allaccount}" var="c">
                        <c:set var="isMentee" value="false" />
                        <c:forEach items="${role}" var="r">
                            <c:if test="${r.id == c.roleid && fn:toLowerCase(r.name) eq 'mentee'}">
                                <c:set var="isMentee" value="true" />
                            </c:if>
                        </c:forEach>

                        <c:if test="${isMentee}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.accountname}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty c.password}">
                                            ******
                                        </c:when>
                                        <c:otherwise>(empty)</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${c.email}</td>
                                <td>
                                    <c:forEach items="${role}" var="r2">
                                        <c:if test="${r2.id == c.roleid}">
                                            ${r2.name}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="text-nowrap">
                                    <!-- View -->
                                    <a href="ViewMenteeAccountDetail?id=${c.id}"
                                       class="btn btn-outline-info btn-sm me-2"
                                       title="View" aria-label="View" data-bs-toggle="tooltip">
                                        <i class="bi bi-eye"></i>
                                    </a>

                                    <!-- Edit -->
                                    <a href="AdminEditAccount?id=${c.id}"
                                       class="btn btn-outline-warning btn-sm me-2"
                                       title="Edit" aria-label="Edit" data-bs-toggle="tooltip">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>

                                    <!-- Delete -->
                                    <form action="AdminDeleteAccount?id=${c.id}" method="post" class="d-inline">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-outline-danger btn-sm"
                                                title="Delete" aria-label="Delete" data-bs-toggle="tooltip"
                                                onclick="return confirm('Are you sure, you want to delete the account: ${c.accountname} ?');">
                                            <i class="bi bi-trash"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>

    <!-- Pagination -->
    <nav aria-label="Page navigation example" style="position: relative">
        <ul class="pagination" style="display: flex; justify-content: center; align-items: center;">
            <c:if test="${tag>1}">
                <li class="page-item">
                    <a class="page-link" href="ViewMenteeAccount?index=${tag-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${endpage}" var="i">
                <li class="page-item">
                    <a class="page-link" href="ViewMenteeAccount?index=${i}">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${tag<endpage}">
                <li class="page-item">
                    <a class="page-link" href="ViewMenteeAccount?index=${tag+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </c:if>
        </ul>
        <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; bottom: 20px; left: 20px;">Back</button>
    </nav>
</c:if>

<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
        tooltipTriggerList.forEach(function (el) { new bootstrap.Tooltip(el) })
    });
</script>
</body>
</html>
