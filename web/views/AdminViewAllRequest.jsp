<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>All Code Requests | Admin Dashboard</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <style>
        body {background-color: #f8f9fa;}
        h2 {font-weight: 600;}
        .breadcrumbs {background: #f1f3f5;padding: 15px 0;margin-bottom: 20px;}
        .breadcrumbs ol {margin-bottom: 0;}
        .table th {background-color: #f1f3f5;text-transform: uppercase;font-size: 14px;}
        .table td {vertical-align: middle;}
        .table-responsive {box-shadow: 0 2px 6px rgba(0,0,0,0.1);border-radius: 8px;overflow: hidden;}
        .pagination .page-link {border-radius: 50% !important;margin: 0 5px;}
        .btn-danger {
            color: #fff !important;
            border: none;
            padding: 6px 12px;
            font-weight: 600;
            border-radius: 8px;
            background-color: #dc3545;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .btn-danger:hover {
            opacity: 0.9;
            transform: translateY(-2px);
            transition: all 0.2s ease-in-out;
        }
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />
<main id="main">
    <section class="breadcrumbs">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2 class="mb-0">All Code Requests</h2>
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item"><a href="AdminDashboard">Dashboard</a></li>
                    <li class="breadcrumb-item active">Code Requests</li>
                </ol>
            </div>
        </div>
    </section>

    <div class="container mb-5">
        <div class="table-responsive bg-white">
            <table class="table table-striped table-hover mb-0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>Deadline</th>
                        <th>Mentee Name</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${coderequest}" var="cr">
                    <tr>
                        <td>${cr.id}</td>
                        <td>${cr.title}</td>
                        <td>${cr.content}</td>
                        <td>${cr.deadline}</td>
                        <td>${cr.mentee.firstname} ${cr.mentee.lastname}</td>
                        <td class="text-center">
                            <form action="DeleteCodeRequest" method="post" 
                                  onsubmit="return confirm('Are you sure you want to delete this request?');"
                                  class="d-inline">
                                <input type="hidden" name="id" value="${cr.id}">
                                <button type="submit" class="btn btn-danger btn-sm d-inline-flex align-items-center">
                                    <i class="bi bi-trash me-1"></i> Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <nav class="mt-4" aria-label="Request pagination">
            <ul class="pagination justify-content-center">
                <c:if test="${tag>1}">
                    <li class="page-item">
                        <a class="page-link" href="AdminViewAllRequest?index=${tag-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${endpage}" var="i">
                    <li class="page-item ${i==tag?'active':''}">
                        <a class="page-link" href="AdminViewAllRequest?index=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${tag<endpage}">
                    <li class="page-item">
                        <a class="page-link" href="AdminViewAllRequest?index=${tag+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <jsp:include page="../guest/Footer.jsp" />
</main>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
