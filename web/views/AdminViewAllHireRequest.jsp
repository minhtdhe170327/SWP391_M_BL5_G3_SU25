<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Hire Request Management</title>
    <meta name="description" content="Quản lý các Hire Request - tìm kiếm, thêm mới, chỉnh sửa, xóa.">
    <meta name="keywords" content="Hire Request, Mentoring, Admin, JSP, Bootstrap">

    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

    <style>
        body {background-color: #f8f9fa;}
        .breadcrumbs {background: #f1f3f5;padding: 15px 0;margin-bottom: 20px;}
        .breadcrumbs ol {margin-bottom: 0;}
        .table th {background-color: #f1f3f5;text-transform: uppercase;font-size: 14px;}
        .table td {vertical-align: middle;}
        .table-responsive {box-shadow: 0 2px 6px rgba(0,0,0,0.1);border-radius: 8px;overflow: hidden;}
        .pagination .page-link {border-radius: 50% !important;margin: 0 5px;}
        .btn {border-radius: 8px;font-weight: 600;}
        .btn:hover {opacity: 0.9;transform: translateY(-2px);transition: all 0.2s ease-in-out;}
        .modal-content {border-radius: 10px;}
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<main id="main" class="mt-4">
    <c:if test="${sessionScope.account.roleid==1 || sessionScope.account.roleid==3}">
        <!-- Breadcrumb -->
        <section class="breadcrumbs">
            <div class="container">
                <div class="d-flex justify-content-between align-items-center">
                    <h2 class="mb-0">Hire Request Management</h2>
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item">
                            <a href="${sessionScope.account.roleid==3 ? 'AdminDashboard' : 'ViewTop3Mentor'}">
                                Home
                            </a>
                        </li>
                        <li class="breadcrumb-item active">Hire Requests</li>
                    </ol>
                </div>
            </div>
        </section>

        <div class="container">
            <!-- Search + Add -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <form action="SearchHireRequest?menteeid=${sessionScope.getmentee.id}" method="post" class="d-flex search-bar flex-grow-1 me-3" style="max-width: 350px;">
                    <input type="text" class="form-control" name="name" placeholder="Search request..." value="${searchtext}">
                    <button type="submit" class="btn btn-secondary"><i class="bi bi-search"></i></button>
                </form>

                <c:if test="${sessionScope.account.roleid==1}">
                    <button class="btn btn-success fw-bold" data-bs-toggle="modal" data-bs-target="#addHireRequestModal">
                        <i class="bi bi-plus-circle me-1"></i> Add Hire Request
                    </button>
                </c:if>
            </div>

            <!-- Hire Request Table -->
            <div class="table-responsive bg-white shadow-sm rounded">
                <table class="table table-striped table-hover mb-0 text-center align-middle">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Mentor Name</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Cost</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:set var="rowIndex" value="1"/>
                    <c:forEach var="c" items="${hirerequest}">
                        <tr>
                            <td>${rowIndex}</td>
                            <td>${c.fullname}</td>
                            <td>${c.title}</td>
                            <td>${c.content}</td>
                            <td>${c.costhire}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:toLowerCase(c.status) eq 'accept' or fn:toLowerCase(c.status) eq 'accepted'}">
                                        <span class="badge bg-success">Accept</span>
                                    </c:when>
                                    <c:when test="${fn:toLowerCase(c.status) eq 'reject' or fn:toLowerCase(c.status) eq 'rejected'}">
                                        <span class="badge bg-danger">Reject</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">${c.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <!-- Delete -->
                                <form action="DeleteHireRequest" method="post" class="d-inline">
                                    <input type="hidden" name="id" value="${c.id}">
                                    <button type="submit" class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this request?');">
                                        <i class="bi bi-trash"></i> Delete
                                    </button>
                                </form>
                            </td>
                        </tr>

                        <!-- Edit Modal -->
                        <div class="modal fade" id="editHireRequestModal${c.id}" tabindex="-1">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content p-3">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Edit Hire Request</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                    </div>
                                    <form action="EditHireRequest" method="post">
                                        <input type="hidden" name="id" value="${c.id}">
                                        <div class="modal-body">
                                            <div class="mb-3">
                                                <label>Title</label>
                                                <input type="text" name="title" value="${c.title}" class="form-control" required>
                                            </div>
                                            <div class="mb-3">
                                                <label>Content</label>
                                                <textarea name="content" class="form-control">${c.content}</textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label>Cost</label>
                                                <input type="number" name="costhire" value="${c.costhire}" class="form-control" required>
                                            </div>
                                            <div class="mb-3">
                                                <label>Status</label>
                                                <select name="status" class="form-select">
                                                    <option value="Accept" ${fn:toLowerCase(c.status) eq 'accept' or fn:toLowerCase(c.status) eq 'accepted' ? 'selected' : ''}>Accept</option>
                                                    <option value="Reject" ${fn:toLowerCase(c.status) eq 'reject' or fn:toLowerCase(c.status) eq 'rejected' ? 'selected' : ''}>Reject</option>
                                                    <option value="Pending" ${fn:toLowerCase(c.status) eq 'pending' ? 'selected' : ''}>Pending</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">Save</button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <c:set var="rowIndex" value="${rowIndex+1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination -->
            <nav class="mt-4" aria-label="HireRequest pagination">
                <ul class="pagination justify-content-center">
                    <c:forEach begin="1" end="${endpage}" var="p">
                        <c:choose>
                            <%-- Nếu là mentee --%>
                            <c:when test="${sessionScope.account.roleid==1}">
                                <li class="page-item ${p == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="AdminViewAllHireRequest?index=${p}&menteeid=${sessionScope.getmentee.id}">
                                        ${p}
                                    </a>
                                </li>
                            </c:when>
                            <%-- Nếu là admin --%>
                            <c:otherwise>
                                <li class="page-item ${p == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="AdminViewAllHireRequest?index=${p}">
                                        ${p}
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </c:if>

    <c:if test="${sessionScope.account.roleid!=1 && sessionScope.account.roleid!=3}">
        <div class="alert alert-danger mt-5 container">
            <i class="bi bi-exclamation-triangle"></i> You do not have permission to access this page.
        </div>
    </c:if>
</main>

<!-- Add Hire Request Modal -->
<div class="modal fade" id="addHireRequestModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-3">
            <div class="modal-header">
                <h5 class="modal-title">Add Hire Request</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <form action="CreateHireRequest" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label>Title</label>
                        <input type="text" name="title" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Content</label>
                        <textarea name="content" class="form-control"></textarea>
                    </div>
                    <div class="mb-3">
                        <label>Cost</label>
                        <input type="number" name="costhire" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Add</button>
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
