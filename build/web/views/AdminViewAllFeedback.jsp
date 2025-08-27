<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>All Feedback</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../guest/Header.jsp" />
<main id="main">
    <section class="breadcrumbs">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2>All Feedback</h2>
                <ol>
                    <li><a href="AdminDashboard">Dashboard</a></li>
                </ol>
            </div>
        </div>
    </section>

    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-start align-items-center h-100">
            <table class="table table-striped table-hover" style="margin: 50px; width:93%">
                <tr>
                    <th>ID</th>
                    <th>Mentee Name</th>
                    <th>Star</th>
                    <th>Comment</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${feedbackList}" var="f">
                    <tr>
                        <td>${f.id}</td>
                        <td>${f.menteeName}</td>
                        <td>${f.star} ⭐</td>
                        <td>${f.comment}</td>
                        <td>
                            <form action="DeleteFeedback" method="post"
                                  onsubmit="return confirm('Are you sure you want to delete this feedback?');">
                                <input type="hidden" name="id" value="${f.id}">
                                <button type="submit" class="btn btn-danger">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <nav aria-label="Page navigation example">
        <ul class="pagination" style="display: flex;justify-content: center;align-items: center;">
            <c:if test="${tag>1}">
                <li class="page-item">
                    <a class="page-link" href="AdminViewAllFeedback?index=${tag-1}">&laquo;</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${endpage}" var="i">
                <li class="page-item">
                    <a class="page-link" href="AdminViewAllFeedback?index=${i}">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${tag<endpage}">
                <li class="page-item">
                    <a class="page-link" href="AdminViewAllFeedback?index=${tag+1}">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <jsp:include page="../guest/Footer.jsp" />
</main>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
