
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header1.jsp" />
<style>
    /* Compact spacing just for this page */
    .compact-page section {
        padding: 40px 0;
    }
    .compact-page .breadcrumbs {
        padding: 8px 0;
    }
    .compact-page .table {
        margin-bottom: 12px;
    }
    .compact-page .pagination {
        margin: 10px 0 0 0;
    }
</style>
<main id="main" class="compact-page" style="position: relative">
    <section class="breadcrumbs">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Feedback</h2>
                <ol>
                    <li><a href="ViewTop3Mentor">Home</a></li>
                    <li>Feedback</li>
                </ol>
            </div>
        </div>
    </section>
    <div class="container">
        <!--                <div class="d-flex mb-3">
                            <form action="SearchRequest?mentorid=${sessionScope.getmentor.id}" method="post" class="flex-grow-1">
                                <div class="input-group">
                                    <input value="${searchtext}" type="text" class="form-control" placeholder="Search" name="name">
                                    <button type="submit" class="btn btn-secondary"><i class="bi-search"></i></button>
                                </div>
                            </form>
                        </div>-->
        <table class="table table-striped table-hover">
            <c:choose>

                <c:when test="${sessionScope.account.roleid == 2}">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Mentee</th>
                            <th>Star</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty listfeedback}">
                        <div class="alert alert-info">No Feedback </div>
                    </c:if>
                    <c:forEach items="${listfeedback}" var="c" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td> <!-- index bắt đầu từ 0, cộng 1 để bắt đầu từ 1 -->
                            <td>
                                <a href="viewFeedback?mentorid=${fbid}&menteeid=${c.menteeid}&coderequestid=${c.id}&fbid=${fbid}">
                                    <c:forEach items="${menteelist1}" var="m">
                                        <c:if test="${m.id == c.menteeid}">
                                            ${m.lastname} ${m.firstname} 
                                        </c:if>
                                    </c:forEach>
                                </a>
                            </td>
                            <td>${c.star}</td>
                            <td>${c.comment}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </c:when>
                <c:otherwise>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Mentor</th>
                            <th>Star</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty listfeedback}">
                        <div class="alert alert-info">No Feedback </div>
                    </c:if>
                    <c:forEach items="${listfeedback}" var="c" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td> <!-- index bắt đầu từ 0, cộng 1 để bắt đầu từ 1 -->
                            <td>
                                <a href="viewFeedback?mentorid=${c.menteeid}&menteeid=${fbid}&coderequestid=${c.id}&fbid=${fbid}">
                                    <c:forEach items="${mentorlist1}" var="m">
                                        <c:if test="${m.id == c.menteeid}">
                                            ${m.lastname} ${m.firstname} 
                                        </c:if>
                                    </c:forEach>
                                </a>
                            </td>
                            <td>${c.star}</td>
                            <td>${c.comment}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </c:otherwise>
            </c:choose>
        </table>



        <nav aria-label="Page navigation example">
            <ul class="pagination" style="display: flex;justify-content: center;align-items: center;">
                <c:if test="${tag>1}">
                    <li class="page-item">
                        <a class="page-link" href="ViewMentorRequest?index=${tag-1}&mentorid=${sessionScope.getmentor.id}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${endpage}" var="i">
                    <li class="page-item"><a class="page-link" href="ViewMentorRequest?index=${i}&mentorid=${sessionScope.getmentor.id}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${tag<endpage}">
                    <li class="page-item">
                        <a class="page-link" href="ViewMentorRequest?index=${tag+1}&mentorid=${sessionScope.getmentor.id}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
            <br>
        </nav>
    </div>
</main>
<jsp:include page="footer1.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
