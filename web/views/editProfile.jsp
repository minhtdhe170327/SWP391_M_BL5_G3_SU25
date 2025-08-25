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
                <form action="${contextPath}/editProfile" method="post" class="profile-body">

                    <c:choose>
                        <%-- ***** TRƯỜNG HỢP 1: MENTEE ***** --%>
                        <c:when test="${sessionScope.account.roleid == 1}">
                            <h4>Edit Mentee Profile</h4>
                            <div class="info-item">
                                <strong>First Name:</strong>
                                <input type="text" name="firstname" value="${sessionScope.getmentee.firstname}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Last Name:</strong>
                                <input type="text" name="lastname" value="${sessionScope.getmentee.lastname}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong>
                                <input type="email" name="email" value="${sessionScope.account.email}" class="form-control" readonly/>
                            </div>
                            <div class="info-item">
                                <strong>Phone Number:</strong>
                                <input type="text" name="phone" value="${sessionScope.getmentee.phone}" class="form-control" pattern="0[0-9]{9}" />
                            </div>
                            <div class="info-item">
                                <strong>Address:</strong>
                                <input type="text" name="address" value="${sessionScope.getmentee.address}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Date of Birth:</strong>
                                <input type="date" name="birthday" value="<fmt:formatDate value='${sessionScope.getmentee.birthday}' pattern='yyyy-MM-dd'/>" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Gender:</strong>
                                <select name="sex" class="form-select">
                                    <option value="M" ${sessionScope.getmentee.sex == 'M' ? 'selected' : ''}>Male</option>
                                    <option value="F" ${sessionScope.getmentee.sex == 'F' ? 'selected' : ''}>Female</option>
                                </select>
                            </div>
                            <div class="info-item">
                                <strong>Introduction:</strong>
                                <textarea name="introduce" class="form-control">${sessionScope.getmentee.introduce}</textarea>
                            </div>
                        </c:when>

                        <%-- ***** TRƯỜNG HỢP 2: MENTOR ***** --%>
                        <c:when test="${sessionScope.account.roleid == 2}">
                            <h4>Edit Mentor Profile</h4>
                            <div class="info-item">
                                <strong>First Name:</strong>
                                <input type="text" name="firstname" value="${sessionScope.getmentor.firstname}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Last Name:</strong>
                                <input type="text" name="lastname" value="${sessionScope.getmentor.lastname}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong>
                                <input type="email" name="email" value="${sessionScope.account.email}" class="form-control" readonly/>
                            </div>
                             <div class="info-item">
                                <strong>Phone Number:</strong>
                                <input type="text" name="phone" value="${sessionScope.getmentor.phone}" class="form-control" pattern="0[0-9]{9}" />
                            </div>
                            <div class="info-item">
                                <strong>Address:</strong>
                                <input type="text" name="address" value="${sessionScope.getmentor.address}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Date of Birth:</strong>
                                <input type="date" name="birthday" value="<fmt:formatDate value='${sessionScope.getmentor.birthday}' pattern='yyyy-MM-dd'/>" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Gender:</strong>
                                <select name="sex" class="form-select">
                                    <option value="M" ${sessionScope.getmentor.sex == 'M' ? 'selected' : ''}>Male</option>
                                    <option value="F" ${sessionScope.getmentor.sex == 'F' ? 'selected' : ''}>Female</option>
                                </select>
                            </div>
                            <div class="info-item">
                                <strong>Introduction:</strong>
                                <textarea name="introduce" class="form-control">${sessionScope.getmentor.introduce}</textarea>
                            </div>
                            <div class="info-item">
                                <strong>Achievement:</strong>
                                <textarea name="achievement" class="form-control">${sessionScope.getmentor.achievement}</textarea>
                            </div>
                            <div class="info-item">
                                <strong>Cost / Hire:</strong>
                                <input type="number" name="cost" value="${sessionScope.getmentor.cost}" class="form-control"/>
                            </div>
                        </c:when>

                        <%-- ***** TRƯỜNG HỢP 3: ADMIN ***** --%>
                        <c:when test="${sessionScope.account.roleid == 3}">
                            <h4>Edit Administrator Profile</h4>
                            <div class="info-item">
                                <strong>Account Name:</strong>
                                <input type="text" name="accountname" value="${sessionScope.account.accountname}" class="form-control"/>
                            </div>
                            <div class="info-item">
                                <strong>Email Address:</strong>
                                <input type="email" name="email" value="${sessionScope.account.email}" class="form-control" readonly/>
                            </div>
                        </c:when>
                    </c:choose>

                    <!-- Nút Save chung -->
                    <div class="mt-4 text-center">
                        <button type="submit" class="btn btn-warning">Save</button>
                    </div>
                </form>

            </div>

        </main>

        <footer>
            <jsp:include page="footer1.jsp" />
        </footer>

    </body>
</html>