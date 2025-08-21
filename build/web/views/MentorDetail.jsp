<%-- 
    Document   : MentorDetail
    Created on : Apr 20, 2025, 1:09:51 PM
    Author     : legen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
        <title>Mentor Detailed</title>
<header>
        <jsp:include page="header1.jsp" />
    <body>
            <main id="main" style="position: relative">
                <!-- ======= Our Portfolio Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Mentor Details</h2>
                        </div>

                    </div>
                </section><!-- End Our Portfolio Section -->

                <!-- ======= Portfolio Details Section ======= -->
                <section id="portfolio-details" class="portfolio-details" >
                    <div class="container">

                        <div class="row gy-4">

                            <div class="col-lg-8">
                                <div class="portfolio-details-slider swiper">
                                    <div class="swiper-wrapper align-items-center">

                                        <div class="swiper-slide">
                                            <img src="assets/img/mentor/${Mentor.avatar}" alt="">
                                    </div>
                                </div>
                                <div class="swiper-pagination"></div>
                            </div>
                        </div>

                        <div class="col-lg-4">
                            <div class="portfolio-info">
                                <h3>${Mentor.name}</h3>
                                <ul>
                                    <li><strong>Sex</strong>: 
                                        <c:if test="${sessionScope.Mentor.sex.equals('M')}">Male</c:if>
                                        <c:if test="${sessionScope.Mentor.sex.equals('F')}">Female</c:if>
                                    </li>
                                    <li><strong>Address</strong>: ${Mentor.address}</li>
                                    <li><strong>Introduce</strong>: ${Mentor.introduce}</li>
                                    <li><strong>Achievement</strong>: ${Mentor.achievement}</li>
                                </ul>
                            </div>
                            <div class="portfolio-description">
                                <h2>More Information</h2>
                                <div>
                                    <button class="btn btn-primary"><a href="ViewMentorInformation?mentorid=${Mentor.id}">More Information</a></button><br>
                                </div>
                                <p>I hope we will have lively discussions after you choose me as your mentor. Thank you very much </p>
                            </div>
                        </div>
                    </div>
                </div>
            </section><!-- End Portfolio Details Section -->
            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>

        </main><!-- End #main -->
       <footer>
        <jsp:include page="footer1.jsp" />
    </footer>
    </body>
</html>

