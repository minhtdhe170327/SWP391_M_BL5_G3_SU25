<%--
    Document   : Footer
    Created on : Aug 15, 2025, 10:10:27 AM
    Author     : Asus TUF
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>Footer</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <link href="${contextPath}/assets/img/favicon.png" rel="icon" />
    <link href="${contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet" />

    <link href="${contextPath}/assets/vendor/animate.css/animate.min.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/aos/aos.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
    <link href="${contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

    <link href="${contextPath}/assets/css/style.css" rel="stylesheet" />

    <style>
        #footer, footer, .footer {
            position: static !important;
            width: 100% !important;
            left: auto !important;
            right: auto !important;
            bottom: auto !important;
            z-index: auto !important;
            background: #112e44;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
    <footer id="footer" data-aos="fade-up" data-aos-easing="ease-in-out" data-aos-duration="500">
        <div class="footer-newsletter">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12" style="text-align: center">
                        <h4>HappyProgamming</h4>
                        <p>
                            You can bring your programming questions to our advisors for answers. Together, progress in programming and become excellent programmers in the future. Nice to meet you.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6 footer-links">
                        <h4>Our Services</h4>
                        <ul>
                            <li><i class="bx bx-chevron-right"></i><a href="${contextPath}/ViewTop3Mentor">Home</a></li>
                            <li><i class="bx bx-chevron-right"></i><a href="${contextPath}/ViewAllMentor">Hire Mentor</a></li>
                            <li><i class="bx bx-chevron-right"></i><a href="#">Create Request</a></li>
                            <li><i class="bx bx-chevron-right"></i><a href="#">View Mentor Answer</a></li>
                        </ul>
                    </div>

                    <div class="col-lg-4 col-md-6 footer-contact">
                        <h4>Contact Us</h4>
                        <p>
                            Thach Hoa, Thach That<br />
                            Viet Nam <br /><br />
                            <strong>Phone:</strong> 0985414324<br />
                            <strong>Email:</strong> happyprogramming@gmail.com<br />
                        </p>
                    </div>

                    <div class="col-lg-4 col-md-6 footer-info">
                        <h3>About HappyProgamming</h3>
                        <p>
                            You can bring your programming questions to our advisors for answers. Together, progress in programming and become excellent programmers in the future. Nice to meet you.
                        </p>
                        <div class="social-links mt-3">
                            <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                            <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                            <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
                            <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong><span>HappyProgamming</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                Designed by <a href="https://bootstrapmade.com/">Team3</a>
            </div>
        </div>
    </footer><script src="${contextPath}/assets/vendor/purecounter/purecounter_vanilla.js"></script>
    <script src="${contextPath}/assets/vendor/aos/aos.js"></script>
    <script src="${contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="${contextPath}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="${contextPath}/assets/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="${contextPath}/assets/vendor/waypoints/noframework.waypoints.js"></script>
    <script src="${contextPath}/assets/vendor/php-email-form/validate.js"></script>

    <script src="${contextPath}/assets/js/main.js"></script>
</body>
</html>