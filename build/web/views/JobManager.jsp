<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Job Management</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<div class="container mt-5 pt-5">
    <c:if test="${sessionScope.account.roleid==3}">
        <!-- Tiêu đề -->
        <h2 class="mb-4 text-center fw-bold">Job Manager</h2>

        <!-- Search + Filter Form -->
        <form action="JobManager" method="get" class="mb-3 d-flex justify-content-between">
            <!-- Ô search -->
            <div class="d-flex" style="flex: 1; max-width: 300px;">
                <input type="text" name="search" class="form-control" placeholder="Search job..." value="${search}">
                <button type="submit" class="btn btn-primary ms-2">
                    <i class="bi bi-search"></i>
                </button>
            </div>
            <!-- Filter -->
            <div>
                <select name="statusFilter" class="form-select" style="width: 150px;" onchange="this.form.submit()">
                    <option value="all" ${statusFilter=='all' ? 'selected' : ''}>All</option>
                    <option value="active" ${statusFilter=='active' ? 'selected' : ''}>Active</option>
                    <option value="inactive" ${statusFilter=='inactive' ? 'selected' : ''}>Inactive</option>
                </select>
            </div>
        </form>

        <!-- Nút Add Job -->
        <div class="mb-3">
            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addJobModal">
                <i class="bi bi-plus-lg"></i> Add Job
            </button>
        </div>

        <!-- Job List Table -->
        <table class="table table-bordered align-middle text-center">
            <thead class="table-light">
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
                        <!-- Nút Edit (mở modal) -->
                        <button class="btn btn-warning btn-sm me-2"
                                data-bs-toggle="modal" 
                                data-bs-target="#editJobModal${job.id}">
                            <i class="bi bi-pencil-square"></i>
                        </button>

                        <!-- Nút Delete -->
                        <form action="JobManager?page=${currentPage}&search=${search}" method="post" style="display:inline;">
                            <input type="hidden" name="jobId" value="${job.id}">
                            <button type="submit" name="action" value="delete" class="btn btn-danger btn-sm"
                                    onclick="return confirm('Are you sure you want to delete this job?');">
                                <i class="bi bi-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>

                <!-- Edit Job Modal -->
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

        <!-- Pagination -->
        <nav>
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="JobManager?page=${i}&search=${search}&statusFilter=${statusFilter}">${i}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>

    <c:if test="${sessionScope.account.roleid!=3}">
        <div class="alert alert-danger mt-5">You do not have permission to access this page.</div>
    </c:if>
</div>

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
</body>
</html>
