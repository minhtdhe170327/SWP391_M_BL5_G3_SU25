<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Skill Management | Admin Dashboard</title>
    <meta name="description" content="Admin Skill Management - Manage all skills with search, add, edit, and delete functions.">
    <meta name="keywords" content="Admin, Skill Management, Java JSP, Bootstrap, SEO">

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <style>
        body {background-color: #f8f9fa;}
        h2 {font-weight: 600;}
        .search-bar .form-control {border-radius: 30px 0 0 30px;}
        .search-bar .btn {border-radius: 0 30px 30px 0;}
        .table th {background-color: #f1f3f5;text-transform: uppercase;font-size: 14px;}
        .table td {vertical-align: middle;}
        .pagination .page-link {border-radius: 50% !important;margin: 0 5px;}
        .btn-info, .btn-warning, .btn-danger, .btn-success, .btn-primary {
            color: #fff !important;
            border: none !important;
            padding: 8px 14px;
            font-weight: 600;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .btn-info {background-color: #17a2b8;}
        .btn-warning {background-color: #f0ad4e;}
        .btn-danger {background-color: #dc3545;}
        .btn-success {background-color: #28a745;}
        .btn-primary {background-color: #007bff;}
        .btn-info:hover, .btn-warning:hover, .btn-danger:hover, .btn-success:hover, .btn-primary:hover {
            opacity: 0.9;
            transform: translateY(-2px);
            transition: all 0.2s ease-in-out;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.account.roleid==3}">
    <jsp:include page="../guest/Header.jsp" />
    <main id="main">
        <!-- Breadcrumb -->
        <section class="breadcrumbs py-3 bg-light mb-3">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <h2 class="mb-0">Skill Management</h2>
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="ViewTop3Mentor">Home</a></li>
                        <li class="breadcrumb-item active">Skills</li>
                    </ol>
                </div>
            </div>
        </section>

        <div class="container">
            <!-- Search + Add -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <form action="AdminSkillManager" method="get" class="search-bar d-flex flex-grow-1 me-3">
                    <input type="text" name="search" class="form-control" 
                           placeholder="Search skills..." value="${search}">
                    <button type="submit" class="btn btn-secondary">
                        <i class="bi bi-search"></i>
                    </button>
                </form>
                <form action="AdminSkillManager" method="post" class="d-flex">
                    <input type="text" name="skillName" class="form-control me-2" 
                           placeholder="Enter new skill name" required>
                    <button type="submit" name="action" value="add" class="btn btn-success fw-bold">
                        <i class="bi bi-plus-circle me-1"></i> Add Skill
                    </button>
                </form>
            </div>

            <!-- Skill Table -->
            <div class="table-responsive shadow-sm rounded bg-white">
                <table class="table table-striped table-hover mb-0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Skill Name</th>
                            <th class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="skill" items="${requestScope.skillList}" varStatus="loop">
                        <tr>
                            <td>${(currentPage-1)*5 + loop.index + 1}</td>
                            <td>
                                <c:if test="${param.editId == skill.id}">
                                    <form action="AdminSkillManager" method="post" class="d-flex">
                                        <input type="hidden" name="skillId" value="${skill.id}">
                                        <input type="text" name="skillName" value="${skill.name}" class="form-control me-2" required>
                                        <button type="submit" name="action" value="update" class="btn btn-primary btn-sm me-2">
                                            <i class="bi bi-check-circle"></i> Save
                                        </button>
                                        <a href="AdminSkillManager?page=${currentPage}&search=${search}" 
                                           class="btn btn-secondary btn-sm">
                                            <i class="bi bi-x-circle"></i> Cancel
                                        </a>
                                    </form>
                                </c:if>
                                <c:if test="${param.editId != skill.id}">
                                    ${skill.name}
                                </c:if>
                            </td>
                            <td class="text-center">
                                <c:if test="${param.editId != skill.id}">
                                    <a href="AdminSkillManager?editId=${skill.id}&page=${currentPage}&search=${search}" 
                                       class="btn btn-warning btn-sm me-1 text-white fw-bold d-inline-flex align-items-center">
                                        <i class="bi bi-pencil-square me-1"></i> Edit
                                    </a>
                                    <form action="AdminSkillManager?page=${currentPage}&search=${search}" 
                                          method="post" class="d-inline">
                                        <input type="hidden" name="skillId" value="${skill.id}">
                                        <button type="submit" name="action" value="delete"
                                                class="btn btn-danger btn-sm fw-bold d-inline-flex align-items-center"
                                                onclick="return confirm('Are you sure you want to delete skill: ${skill.name}?');">
                                            <i class="bi bi-trash me-1"></i> Delete
                                        </button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination -->
            <nav class="mt-4" aria-label="Skill pagination">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="AdminSkillManager?page=${i}&search=${search}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </main>
</c:if>
<c:if test="${sessionScope.account.roleid!=3}">
    <div class="alert alert-danger mt-5 container">
        <i class="bi bi-exclamation-triangle"></i> You do not have permission to access this page.
    </div>
</c:if>
<jsp:include page="../guest/Footer.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
