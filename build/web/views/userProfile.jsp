<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty sessionScope.account}">
    <c:redirect url="Login" />
</c:if>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        <jsp:include page="header1.jsp" /> 
        <style>
            body {
                background-color: #f4f7f6;
            }
            .profile-container {
                max-width: 900px;
                margin-top: 120px;
                margin-bottom: 50px;
            }
            .profile-card {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                overflow: hidden;
            }
            .profile-header {
                background-color: #0d47a1;
                color: white;
                padding: 30px;
                text-align: center;
            }
            .profile-body {
                padding: 30px;
            }
            .profile-body h4 {
                color: #0d47a1;
                border-bottom: 2px solid #eee;
                padding-bottom: 10px;
                margin-bottom: 20px;
            }
            .info-item {
                display: flex;
                align-items: flex-start;
                margin-bottom: 15px;
            }
            .info-item strong {
                min-width: 150px;
                color: #555;
            }
            .star-rating {
                color: #ffc107;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="header1.jsp" />
        </header>

        <main class="container profile-container">
            <div class="profile-card">

                <c:set var="displayName">
                    <c:choose>
                        <c:when test="${sessionScope.account.roleid == 1}">${sessionScope.getmentee.name}</c:when>
                        <c:when test="${sessionScope.account.roleid == 2}">${sessionScope.getmentor.name}</c:when>
                        <c:when test="${sessionScope.account.roleid == 3}">${sessionScope.account.accountname}</c:when>
                    </c:choose>
                </c:set>

                <div class="profile-header">
                    <h2>${displayName}</h2>
                    <p>${sessionScope.account.email}</p>
                </div>

                <div class="profile-body">

                    <c:choose>
                        <%-- ***** TRƯỜNG HỢP 1: MENTEE ***** --%>
                        <c:when test="${sessionScope.account.roleid == 1}">
                            <h4>My Mentee Profile</h4>
                            <div class="info-item">
                                <strong>Full Name:</strong> <span>${sessionScope.getmentee.name}</span>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong> <span>${sessionScope.account.email}</span>
                            </div>
                            <div class="info-item">
                                <strong>Phone Number:</strong> <span>${sessionScope.getmentee.phone}</span>
                            </div>
                            <div class="info-item">
                                <strong>Address:</strong> <span>${sessionScope.getmentee.address}</span>
                            </div>
                            <div class="info-item">
                                <strong>Date of Birth:</strong> <span><fmt:formatDate value="${sessionScope.getmentee.birthday}" pattern="dd/MM/yyyy" /></span>
                            </div>
                            <div class="info-item">
                                <strong>Gender:</strong> <span>${sessionScope.getmentee.sex == 'M' ? 'Male' : 'Female'}</span>
                            </div>
                            <div class="info-item">
                                <strong>Introduction:</strong> <span>${sessionScope.getmentee.introduce}</span>
                            </div>
                            <div class="info-item">
                                <strong>Role:</strong> <span class="badge bg-primary">Mentee</span>
                            </div>
                        </c:when>

                        <%-- ***** TRƯỜNG HỢP 2: MENTOR ***** --%>
                        <c:when test="${sessionScope.account.roleid == 2}">
                            <h4>My Mentor Profile</h4>
                            <div class="info-item">
                                <strong>Full Name:</strong> <span>${sessionScope.getmentor.name}</span>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong> <span>${sessionScope.account.email}</span>
                            </div>
                            <div class="info-item">
                                <strong>Phone Number:</strong> <span>${sessionScope.getmentor.phone}</span>
                            </div>
                            <div class="info-item">
                                <strong>Address:</strong> <span>${sessionScope.getmentor.address}</span>
                            </div>
                            <div class="info-item">
                                <strong>Date of Birth:</strong> <span><fmt:formatDate value="${sessionScope.getmentor.birthday}" pattern="dd/MM/yyyy" /></span>
                            </div>
                            <div class="info-item">
                                <strong>Gender:</strong> <span>${sessionScope.getmentor.sex}</span>
                            </div>
                            <div class="info-item">
                                <strong>Introduction:</strong> <span>${sessionScope.getmentor.introduce}</span>
                            </div>
                            <div class="info-item">
                                <strong>Achievement:</strong> <span>${sessionScope.getmentor.achievement}</span>
                            </div>
                            <div class="info-item">
                                <strong>Cost / Hire:</strong>
                                <span><fmt:formatNumber value="${sessionScope.getmentor.cost}" type="currency" currencySymbol="" maxFractionDigits="0" /></span>
                            </div>
                            <div class="info-item">
                                <strong>Role:</strong> <span class="badge bg-success">Mentor</span>
                            </div>
                        </c:when>

                        <%-- ========================================================= --%>
                        <%-- ***** TRƯỜNG HỢP 3: ADMIN (ĐÃ CẬP NHẬT)              ***** --%>
                        <%-- ========================================================= --%>
                        <c:when test="${sessionScope.account.roleid == 3}">
                            <h4>Administrator Profile</h4>
                            <div class="info-item">
                                <strong>Account Name:</strong>
                                <span>${sessionScope.account.accountname}</span>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong>
                                <span>${sessionScope.account.email}</span>
                            </div>
                            <div class="info-item">
                                <strong>Role:</strong>
                                <span class="badge bg-danger">Administrator</span>
                            </div>
                            <hr/>
                            <a href="${contextPath}/AdminDashboard" class="btn btn-danger">Go to Dashboard</a>
                        </c:when>
                    </c:choose>
                    <div class="mt-4 text-center">
                        <a href="${contextPath}/editProfile" class="btn btn-warning">Edit Profile</a>
                    </div>
                </div>
            </div>
        </main>

        <footer>
            <jsp:include page="footer1.jsp" />
        </footer>

    </body>
</html>