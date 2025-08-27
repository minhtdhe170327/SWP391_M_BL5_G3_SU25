<%-- 
    Document   : MentorRequestDetail
    Created on : Aug 25, 2025, 5:37:18 PM
    Author     : Asus TUF
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header1.jsp" />

<main id="main" style="position: relative">
    <section class="breadcrumbs">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Request Detail</h2>
                <ol>
                    <li><a href="ViewTop3Mentor">Home</a></li>
                </ol>
            </div>
        </div>
    </section>

    <div class="container mb-5">
        <!-- Actions: right aligned -->
        <div class="d-flex justify-content-end mb-3">
            <c:if test="${answer != null}">
                <form action="<%=request.getContextPath()%>/views/UpdateAnswer.jsp" method="post">
                    <input type="hidden" name="mentorid" value="${sessionScope.getmentor.id}"/>
                    <input type="hidden" name="answerid" value="${answer.id}"/>
                    <button type="submit" class="btn btn-outline-primary">Edit Answer</button>
                </form>
            </c:if>
            <c:if test="${answer == null}">
                <form action="<%=request.getContextPath()%>/views/CreateAnswer.jsp" method="post">
                    <button type="submit" class="btn btn-primary">New Answer</button>
                </form>
            </c:if>
        </div>

        <!-- Main card -->
        <div class="card shadow-sm" style="border-radius: 12px;">
            <div class="card-body">
                <h5 class="text-center mb-4">Title: <span class="fw-semibold">${coderequest.title}</span></h5>

                <div class="row g-3 justify-content-center">
                    <div class="col-md-10 col-lg-8">
                        <div class="p-3 bg-light" style="border-radius: 10px;">
                            <div class="mb-2"><strong>Content:</strong> ${coderequest.content}</div>
                            <div class="mb-2"><strong>Deadline:</strong> ${coderequest.deadline}</div>
                            <div class="mb-2">
                                <strong>Skill:</strong>
                                <c:forEach items="${skill}" var="s">
                                    <span class="badge bg-secondary me-1">${s.name}</span>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <hr class="my-4"/>
                <h5 class="text-center mb-3">Answer</h5>
                <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-8">
                        <div class="p-4" style="background: #fafafa; border-radius: 10px; min-height: 120px;">
                            <c:choose>
                                <c:when test="${answer != null}">
                                    <div class="mb-2"><strong>Content:</strong></div>
                                    <div>${answer.content}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="text-muted">Mentor doesn't answer this request.</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Back button bottom-left -->
        <div class="mt-3">
            <button onclick='window.history.go(-1);' class="btn btn-outline-primary btn-lg">Back</button>
        </div>
    </div>
</main>

<jsp:include page="footer1.jsp" />


