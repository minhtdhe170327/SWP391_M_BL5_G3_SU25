<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Mentor Account List | Admin Dashboard</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        body {background-color: #f8f9fa;}
        h2 {font-weight: 600;}
        .search-bar .form-control {border-radius: 30px 0 0 30px;}
        .search-bar .btn {border-radius: 0 30px 30px 0;}
        .table th {background-color: #f1f3f5;text-transform: uppercase;font-size: 14px;}
        .table td {vertical-align: middle;}
        .pagination .page-link {border-radius: 50% !important;margin: 0 5px;}
        .btn-back {position: absolute;bottom: 20px;left: 20px;}
        .btn-info, .btn-warning, .btn-danger {
            color: #fff !important;
            border: none !important;
            padding: 8px 14px;
            font-weight: 600;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .btn-info {background-color: #17a2b8;}
        .btn-warning {background-color: #f0ad4e;}
        .btn-danger {background-color: #dc3545;}
        .btn-info:hover, .btn-warning:hover, .btn-danger:hover {
            opacity: 0.9;
            transform: translateY(-2px);
            transition: all 0.2s ease-in-out;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.account.roleid==3}">
    <jsp:include page="../guest/Header.jsp" />
    <main id="main">
        <section class="breadcrumbs py-3 bg-light mb-3">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <h2 class="mb-0">Mentor Accounts</h2>
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="ViewTop3Mentor">Home</a></li>
                        <li class="breadcrumb-item active">Accounts</li>
                    </ol>
                </div>
            </div>
        </section>
        <div class="container">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <form action="SearchAccount" method="post" class="search-bar d-flex flex-grow-1 me-3">
                    <input value="${searchtext}" type="text" class="form-control" placeholder="Search Mentor accounts..." name="name">
                    <button type="submit" class="btn btn-secondary"><i class="bi bi-search"></i></button>
                </form>
                <a href="AdminAddMentorAccount" class="btn btn-success fw-bold">
                    <i class="bi bi-person-plus me-2"></i> Add Mentor
                </a>
            </div>
            <div class="table-responsive shadow-sm rounded bg-white">
                <table class="table table-striped table-hover mb-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Account Name</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${allaccount}" var="c">
                        <c:set var="isMentor" value="false" />
                        <c:forEach items="${role}" var="r">
                            <c:if test="${r.id == c.roleid && fn:toLowerCase(r.name) eq 'mentor'}">
                                <c:set var="isMentor" value="true" />
                            </c:if>
                        </c:forEach>
                        <c:if test="${isMentor}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.accountname}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty c.password}">••••••</c:when>
                                        <c:otherwise><span class="text-muted">(empty)</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${c.email}</td>
                                <td>
                                    <c:forEach items="${role}" var="r2">
                                        <c:if test="${r2.id == c.roleid}">${r2.name}</c:if>
                                    </c:forEach>
                                </td>
                                <td class="text-center">
                                    <a href="ViewMentorAccountDetail?id=${c.id}" 
                                       class="btn btn-info btn-sm me-1 text-white fw-bold d-inline-flex align-items-center" 
                                       title="View">
                                        <i class="bi bi-eye me-1"></i> View
                                    </a>
                                    <a href="AdminEditMentorAccount?id=${c.id}" 
                                       class="btn btn-warning btn-sm me-1 text-white fw-bold d-inline-flex align-items-center" 
                                       title="Edit">
                                        <i class="bi bi-pencil-square me-1"></i> Edit
                                    </a>
                                    <form action="AdminDeleteAccount?id=${c.id}" method="post" class="d-inline">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-danger btn-sm fw-bold d-inline-flex align-items-center text-white"
                                                onclick="return confirm('Are you sure you want to delete account: ${c.accountname}?');"
                                                title="Delete">
                                            <i class="bi bi-trash me-1"></i> Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav class="mt-4" aria-label="Account pagination">
                <ul class="pagination justify-content-center">
                    <c:if test="${tag>1}">
                        <li class="page-item">
                            <a class="page-link" href="ViewMentorAccount?index=${tag-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${endpage}" var="i">
                        <li class="page-item ${i==tag?'active':''}">
                            <a class="page-link" href="ViewMentorAccount?index=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${tag<endpage}">
                        <li class="page-item">
                            <a class="page-link" href="ViewMentorAccount?index=${tag+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            <button onclick="window.history.back()" class="btn btn-outline-primary btn-back">← Back</button>
        </div>
    </main>
</c:if>
    <jsp:include page="../guest/Footer.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
