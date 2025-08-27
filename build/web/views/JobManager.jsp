<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Job Management | Admin Dashboard</title>
    <meta name="description" content="Manage all jobs with search, filter, add, edit, and delete options.">
    <meta name="keywords" content="Admin, Job Management, JSP, Bootstrap">

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <style>
        body {background-color: #f8f9fa;}
        h2 {font-weight: 600;}
        .breadcrumbs {background: #f1f3f5;padding: 15px 0;margin-bottom: 20px;}
        .breadcrumbs ol {margin-bottom: 0;}
        .search-bar .form-control {border-radius: 30px 0 0 30px;}
        .search-bar .btn {border-radius: 0 30px 30px 0;}
        .table th {background-color: #f1f3f5;text-transform: uppercase;font-size: 14px;}
        .table td {vertical-align: middle;}
        .table-responsive {box-shadow: 0 2px 6px rgba(0,0,0,0.1);border-radius: 8px;overflow: hidden;}
        .pagination .page-link {border-radius: 50% !important;margin: 0 5px;}
        .btn-warning, .btn-danger, .btn-success, .btn-primary {
            color: #fff !important;
            font-weight: 600;
            border-radius: 8px;
            padding: 6px 12px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .btn-warning {background-color: #f0ad4e;}
        .btn-danger {background-color: #dc3545;}
        .btn-success {background-color: #28a745;}
        .btn-primary {background-color: #007bff;}
        .btn:hover {opacity: 0.9;transform: translateY(-2px);transition: all 0.2s ease-in-out;}
        .modal-content {border-radius: 10px;}
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<main id="main" class="mt-4">
    <c:if test="${sessionScope.account.roleid==3}">
        <!-- Breadcrumb -->
        <section class="breadcrumbs">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <h2 class="mb-0">Job Management</h2>
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="AdminDashboard">Dashboard</a></li>
                        <li class="breadcrumb-item active">Jobs</li>
                    </ol>
                </div>
            </div>
        </section>

        <div class="container">
            <!-- Search + Filter -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <form action="JobManager" method="get" class="search-bar d-flex flex-grow-1 me-3" style="max-width: 350px;">
                    <input type="text" name="search" class="form-control" placeholder="Search job..." value="${search}">
                    <button type="submit" class="btn btn-secondary"><i class="bi bi-search"></i></button>
                </form>
                <form action="JobManager" method="get">
                    <select name="statusFilter" class="form-select" style="width: 150px;" onchange="this.form.submit()">
                        <option value="all" ${statusFilter=='all' ? 'selected' : ''}>All</option>
                        <option value="active" ${statusFilter=='active' ? 'selected' : ''}>Active</option>
                        <option value="inactive" ${statusFilter=='inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                </form>
                <button class="btn btn-success fw-bold ms-3" data-bs-toggle="modal" data-bs-target="#addJobModal">
                    <i class="bi bi-plus-circle me-1"></i> Add Job
                </button>
            </div>

            <!-- Job Table -->
            <div class="table-responsive bg-white shadow-sm rounded">
                <table class="table table-striped table-hover mb-0 text-center align-middle">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Job Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="job" items="${requestScope.jobList}" varStatus="loop">
                        <tr>
                            <td>${(currentPage-1)*5 + loop.index + 1}</td>
                            <td>${job.jobname}</td>
                            <td>${job.description}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${job.status eq 'active'}">
                                        <span class="badge bg-success">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <!-- Edit button -->
                                <button class="btn btn-warning btn-sm me-2" data-bs-toggle="modal" data-bs-target="#editJobModal${job.id}">
                                    <i class="bi bi-pencil-square"></i> Edit
                                </button>
                                <!-- Delete -->
                                <form action="JobManager?page=${currentPage}&search=${search}" method="post" class="d-inline">
                                    <input type="hidden" name="jobId" value="${job.id}">
                                    <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this job?');">
                                        <i class="bi bi-trash"></i> Delete
                                    </button>
                                </form>
                            </td>
                        </tr>

                        <!-- Edit Modal -->
                        <div class="modal fade" id="editJobModal${job.id}" tabindex="-1">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content p-3">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit Job</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <form action="JobManager" method="post">
                                        <input type="hidden" name="jobId" value="${job.id}">
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label>Job Name</label>
                                                <input type="text" name="jobName" value="${job.jobname}" class="form-control" required>
                                            </div>
                                            <div class="mb-3">
                                                <label>Description</label>
                                                <input type="text" name="description" value="${job.description}" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label>Status</label>
                                                <select name="status" class="form-select">
                                                    <option value="active" ${job.status eq 'active' ? 'selected' : ''}>Active</option>
                                                    <option value="inactive" ${job.status eq 'inactive' ? 'selected' : ''}>Inactive</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" name="action" value="update" class="btn btn-primary">Save</button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination -->
            <nav class="mt-4" aria-label="Job pagination">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="JobManager?page=${i}&search=${search}&statusFilter=${statusFilter}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </c:if>

    <c:if test="${sessionScope.account.roleid!=3}">
        <div class="alert alert-danger mt-5 container">
            <i class="bi bi-exclamation-triangle"></i> You do not have permission to access this page.
        </div>
    </c:if>
</main>

<!-- Add Job Modal -->
<div class="modal fade" id="addJobModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-3">
            <div class="modal-header">
                <h5 class="modal-title">Add Job</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="JobManager" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label>Job Name</label>
                        <input type="text" name="jobName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Description</label>
                        <input type="text" name="description" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label>Status</label>
                        <select name="status" class="form-select">
                            <option value="active" selected>Active</option>
                            <option value="inactive">Inactive</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" name="action" value="add" class="btn btn-success">Add</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../guest/Footer.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
