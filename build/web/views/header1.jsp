<%--
    Document   : Header
    Created on : Aug 15, 2025, 10:14:48 AM
    Author     : Asus TUF
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Đặt biến contextPath để mọi đường dẫn luôn chính xác --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>HomePage</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <link href="${contextPath}/assets/img/favicon.png" rel="icon">
        <link href="${contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet">

        <link href="${contextPath}/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="${contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <link href="${contextPath}/assets/css/style.css" rel="stylesheet">
    </head>

    <body>
        <header id="header" class="fixed-top d-flex align-items-center ">
            <div class="container d-flex justify-content-between align-items-center">

                <div class="logo">
                    <h1 class="text-light"><a href="${contextPath}/ViewTop3Mentor"><span>HappyProgamming</span></a></h1>
                </div>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="" href="${contextPath}/ViewTop3Mentor">Home</a></li>
                        <li><a href="${contextPath}/ViewAllMentor">Mentor</a></li>

                        <%-- Logic cho người dùng khách --%>
                        <c:if test="${empty sessionScope.account}">
                            <li><a href="${contextPath}/Login">Sign In</a></li>
                            <li><a href="${contextPath}/Register.jsp">Sign Up</a></li>
                            </c:if>

                        <%-- Logic cho người dùng đã đăng nhập --%>
                        <c:if test="${not empty sessionScope.account}">
                            <li class="dropdown">
                                <a href="#">
                                    <span>
                                        Hello
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.getmentee}">${sessionScope.getmentee.firstname}</c:when>
                                            <c:when test="${not empty sessionScope.getmentor}">${sessionScope.getmentor.firstname}</c:when>
                                            <c:otherwise>User</c:otherwise>
                                        </c:choose>
                                    </span>
                                    <i class="bi bi-chevron-down"></i>
                                </a>
                                <%-- Menu dropdown con đã được đặt đúng vị trí --%>
                                <ul>
                                    <li><a href="${contextPath}/ChangePassword.jsp">Change Password</a></li>

                                    <%-- Links cho Mentee --%>
                                    <c:if test="${not empty sessionScope.getmentee}">
                                        <li><a href="viewProfile">Profile</a></li>
                                        <li><a href="${contextPath}/ViewAllRequest?menteeid=${sessionScope.getmentee.id}">My Request</a></li>
                                        <li><a href="${contextPath}/ViewAllHireRequest?menteeid=${sessionScope.getmentee.id}">My Hire Request</a></li>
                                        <li><a href="${contextPath}/MyPost">My Posts</a></li>
                                        <li><a href="${contextPath}/SavedPost">Favorite Post</a></li>
                                        </c:if>

                                    <%-- Links cho Mentor (dựa trên roleid hoặc session object) --%>
                                    <c:if test="${sessionScope.account.roleid == 2 and not empty sessionScope.getmentor}">
                                        <li><a href="viewProfile">Profile</a></li>
                                        <li><a href="${contextPath}/ViewMentorRequest?mentorid=${sessionScope.getmentor.id}">My Request</a></li>
                                        <li><a href="${contextPath}/ViewMentorHireRequest?mentorid=${sessionScope.getmentor.id}">Hire Requests</a></li>
                                        <li><a href="${contextPath}/mentor-analytics?mentorid=${sessionScope.getmentor.id}">Analytics Dashboard</a></li>
                                        <li><a href="${contextPath}/MyPost">My Posts</a></li>
                                        <li><a href="${contextPath}/SavedPost">Favorite Post</a></li>
                                        </c:if>

                                    <%-- Links cho Admin --%>
                                    <c:if test="${sessionScope.account.roleid == 3}">
                                        <li><a href="viewProfile">Profile</a></li>
                                        <li><a href="${contextPath}/ViewAllAccount">Account List</a></li>
                                        <li><a href="${contextPath}/ViewAllMentee">Mentee List</a></li>
                                        <li><a href="${contextPath}/AdminSkillManager">Skill Manager</a></li>
                                        <li><a href="${contextPath}/JobManager">Job Manager</a></li>
                                        </c:if>

                                    <li><a href="${contextPath}/LogOut?accountid=${sessionScope.account.id}">Sign Out</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav></div>
        </header><script src="${contextPath}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="${contextPath}/assets/vendor/aos/aos.js"></script>
        <script src="${contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="${contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="${contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="${contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="${contextPath}/assets/vendor/php-email-form/validate.js"></script>

        <script src="${contextPath}/assets/js/main.js"></script>

        <%-- File Header không nên chứa thẻ đóng </body> và </html>
             vì nó sẽ được include vào các trang khác đã có các thẻ này. --%>