<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entity.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>FeedBack Detail</title>
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

        <!-- =======================================================
        * Template Name: Moderna - v4.11.0
        * Template URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
   <header>
                        <jsp:include page="header1.jsp" />

        </header>
    </head>
    <body>
        <%--<c:if test="${sessionScope.account.roleid==1}">--%>
        
          <div class="portfolio-info">
              <br>              <br>
              <br>              <br>


                                    <h3 style="text-align: center;">Answer</h3>
                                    <c:if test="${answer==null}">
                                        <ul>
                                            <h3>Mentor doesn't answer this request.</h3>
                                        </ul>
                                    </c:if>
                                    <c:if test="${answer!=null}">
                                        <ul>
                                            <h3>Content: </h3><br>
                                            <p style="text-align: center">${answer.content}</p>                                           
                                        </ul>

                                    </c:if>
                                </div>

                                <div class="portfolio-info">
                                    <h3 style="text-align: center;">Feedback</h3>
                                    <c:if test="${feedback==null}">
                                        <ul>
                                            <h3>Don't have feedback</h3>
                                            <c:if test="${answer!=null}">
                                                <form action="CreateFeedback?answerid=${answer.id}" method="post">
                                                    <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 200px;height: 50px;" >Create Feedback</button>
                                                </form>
                                            </c:if>
                                        </ul>
                                    </c:if>
                                    <%
                                        Feedback f= (Feedback) request.getAttribute("feedback");
                                    %>
                                    <c:if test="${feedback!=null}">
                                        <ul>
                                            <strong><p style="text-align: center">RateStar: ${feedback.star}</p></strong><br>
                                            <div style="text-align: center">
                                                <% for(int i=0;i<f.getStar();i++){ %>
                                                <span id="boot-icon" class="bi bi-star" style="font-size: 56px; color: rgb(255, 210, 48); text-align: center"></span>
                                                <%}%>
                                            </div>
                                            <br>
                                            <strong><p style="text-align: center">Comment:</p></strong>
                                            <p style="text-align: center">${feedback.comment}</p>  
<!--                                            <form action="EditFeedback?feedbackid=${feedback.id}" method="post">
                                                <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 200px;height: 50px;" >Edit Feedback</button>
                                            </form>-->

                                        </ul>

                                    </c:if>
                                </div>
                                    <ol>
                    <a href="ViewTop3Mentor">Home</a> /  <a href="listFeedback?fbid=${fbid}">Feedback</a>
                   
                
            <footer>
                <jsp:include page="footer1.jsp" />
            </footer>            <!-- Vendor JS Files -->
   
        <%--</c:if>--%>
        
    </body>
</html>


    </body>
</html>
