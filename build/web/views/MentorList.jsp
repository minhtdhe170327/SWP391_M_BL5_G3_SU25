<%-- 
    Document   : MentorList
    Created on : Apr 19, 2025, 12:44:28 PM
    Author     : legen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "entity.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Mentor List</title>
<header>
        <jsp:include page="header1.jsp" />
    </header>
    </head>
    <body>
       
            <main id="main">
                <!-- ======= Our Team Section ======= -->
                <section class="breadcrumbs">
                    <div class="container">

                        <div class="d-flex justify-content-between align-items-center">
                            <h2>Mentor List</h2>
                            <ol>
                                <li><a href="ViewTop3Mentor">Home</a></li>
                            </ol>
                        </div>

                    </div>
                </section><!-- End Our Team Section -->
              <div class="d-flex justify-content-center" style="margin: 10px;">
                    <form action="SearchMentor" method="post">
                        <div class="input-group" style="margin: 10px; padding-right: 500px;padding-left:  100px; ">
                            <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                        <button type="submit" class="btn btn-secondary" ><i class="bi-search"></i></button>
                    </div>

                </form>
              <c:if test="${sessionScope.account.roleid==3}">
                <form action="NewMentorAccount.jsp" method="post">
                    <button type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px;margin-right: 50px;font-size: 15px;float: right; width: 300px;height: 50px;" >Add Mentor Account</button>
                </form>
                </c:if>
            </div>
            <!-- ======= Team Section ======= -->
            <section class="team" data-aos="fade-up" data-aos-easing="ease-in-out" data-aos-duration="500">
                <div class="container">

                    <div class="row">
                        <c:forEach items="${allMentor}" var="m">
                            <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
                                <div class="member">
                                    <div class="member-img">
                                        <a href="ViewMentorDetail?mentorid=${m.id}"><img src="assets/img/mentor/${m.avatar}" class="img-fluid" alt=""></a>
                                        <div class="social">
                                            <a href=""><i class="bi bi-twitter"></i></a>
                                            <a href=""><i class="bi bi-facebook"></i></a>
                                            <a href=""><i class="bi bi-instagram"></i></a>
                                            <a href=""><i class="bi bi-linkedin"></i></a>
                                        </div>
                                    </div>
                                    <div class="member-info">
                                        <h4>${m.name}</h4>
                                        <span>Developer, Tester</span>
                                        <p>${m.introduce}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section><!-- End Team Section -->


        </main><!-- End #main -->
        <nav aria-label="Page navigation example" style="position: relative">
            <ul class="pagination" style="display: flex;justify-content: center;align-items: center;  ">
                <c:if test="${tag>1}">
                    <li class="page-item">
                        <a class="page-link" href="ViewAllMentor?index=${tag-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${endpage}" var="i">
                    <li class="page-item"><a class="page-link" href="ViewAllMentor?index=${i}">${i}</a></li>
                    </c:forEach>

                <c:if test="${tag<enpage}">
                    <li class="page-item">
                        <a class="page-link" href="ViewAllMentor?index=${tag+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
                <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg" style="position: absolute;bottom: 20px;left: 20px;">Back</button>
            </ul>
        </nav>
        <footer>
        <jsp:include page="footer1.jsp" />
    </footer>
    </body>
</html>
