<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>AccountList</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
    </head>
    <body>
        <c:if test="${sessionScope.account.roleid==3}">
            <jsp:include page="../guest/Header.jsp" />
            <main id="main" style="position: relative">
                <section class="breadcrumbs">
                    <div class="container">
                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Account List (Mentor Only)</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>
                    </div>
                </section>

                <div class="d-flex">
                    <!-- Search: vẫn hoạt động bình thường; bảng phía dưới chỉ render Mentor -->
                    <form action="SearchAccount" method="post">
                        <div class="input-group" style="margin: 10px; padding-right: 500px; padding-left: 100px;">
                            <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                            <button type="submit" class="btn btn-secondary"><i class="bi-search"></i></button>
                        </div>
                    </form>
                    <a href="AdminAddMenteeAccount" 
                class="btn btn-success btn-lg d-flex align-items-center justify-content-center"
                style="margin-top: 10px; margin-right: 50px; font-size: 15px; float: right; width: 300px; height: 50px;">
                <i class="bi bi-person-plus me-2"></i> Add Mentor Account
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
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--
                                   Chỉ render hàng nếu account có role là Mentor.
                                   So sánh theo tên role (case-insensitive) để không phụ thuộc roleid.
                                -->
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
                                            <!-- Mask password để an toàn -->
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty c.password}">
                                                        ****** <!-- hoặc ${fn:substring(c.password, 0, 2)}**** -->
                                                    </c:when>
                                                    <c:otherwise>
                                                        (empty)
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${c.email}</td>

                                            <!-- In ra đúng nhãn role -->
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
                            <a class="page-link" href="ViewMentorAccount?index=${tag-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${endpage}" var="i">
                        <li class="page-item">
                            <a class="page-link" href="ViewMentorAccount?index=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- Sửa typo: enpage -> endpage -->
                    <c:if test="${tag<endpage}">
                        <li class="page-item">
                            <a class="page-link" href="ViewMentorAccount?index=${tag+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute; bottom: 20px; left: 20px;">Back</button>
            </nav>

            
        </c:if>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
    </body>
</html>
