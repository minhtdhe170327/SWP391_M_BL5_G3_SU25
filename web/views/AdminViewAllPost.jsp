<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>All Posts | Blog</title>
    <meta name="description" content="All posts from our community, browse, search and read articles.">
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        body {background-color: #f8f9fa;}
        h2 {font-weight: 600;}
        .search-bar .form-control {border-radius: 30px 0 0 30px;}
        .search-bar .btn {border-radius: 0 30px 30px 0;}
        .card {
            border: 1px solid #e9ecef;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
            transition: transform .2s, box-shadow .2s;
        }
        .card:hover {
            transform: translateY(-4px);
            box-shadow: 0 6px 18px rgba(0,0,0,0.12);
        }
        .card-title {font-size: 1.1rem;}
        .post-meta {font-size: 0.85rem; color: #6c757d;}
        .card-text {font-size: 0.95rem; line-height: 1.5; color: #495057;}
        .pagination .page-link {border-radius: 50%!important; margin: 0 4px;}
        .btn-create {font-weight: 600; border-radius: 8px;}
    </style>
</head>
<body>
<jsp:include page="../guest/Header.jsp" />

<main id="main">
    <!-- Breadcrumbs -->
    <section class="breadcrumbs py-3 bg-light mb-3">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h2 class="mb-0">All Posts</h2>
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item"><a href="ViewTop3Mentor">Home</a></li>
                    <li class="breadcrumb-item active">Posts</li>
                </ol>
            </div>
        </div>
    </section>

    <div class="container">
        <!-- Search + Create -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <form action="ViewAllPost" method="GET" class="search-bar d-flex flex-grow-1 me-3">
                <input type="text" name="keyword" class="form-control" placeholder="Search posts..." value="${param.keyword}">
                <button type="submit" class="btn btn-secondary"><i class="bi bi-search"></i></button>
            </form>
            <c:if test="${sessionScope.account != null}">
                <a href="CreatePost" class="btn btn-success btn-create">
                    <i class="bi bi-plus-circle me-1"></i> New Post
                </a>
            </c:if>
        </div>

        <!-- Posts Grid -->
        <div class="row">
            <c:forEach var="post" items="${posts}">
                <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body d-flex flex-column">
                            <!-- Title -->
                            <h5 class="card-title fw-bold text-dark mb-2">${post.title}</h5>

                            <!-- Meta -->
                            <div class="post-meta mb-2">
                                <i class="bi bi-calendar"></i> <time>${post.createdDate}</time>
                            </div>

                            <!-- Excerpt -->
                            <p class="card-text mb-3 flex-grow-1">${post.content}</p>

                            <!-- Actions -->
                            <div class="d-flex justify-content-between mt-auto">
                                <a href="ViewPostDetail?id=${post.id}" class="btn btn-outline-primary btn-sm">
                                    <i class="bi bi-book"></i> Read More
                                </a>

                                <c:if test="${sessionScope.account != null && sessionScope.account.roleid == 3}">
                                    <form action="DeletePost" method="post" class="d-inline"
                                          onsubmit="return confirm('Delete this post: ${post.title}?');">
                                        <input type="hidden" name="postId" value="${post.id}">
                                        <button type="submit" class="btn btn-outline-danger btn-sm">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Pagination -->
        <c:if test="${totalPages > 1}">
            <nav class="mt-4" aria-label="Posts pagination">
                <ul class="pagination justify-content-center">
                    <li class="page-item ${currentPage==1?'disabled':''}">
                        <a class="page-link" href="AdminViewAllPost?page=${currentPage-1}" aria-label="Previous">&laquo;</a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage==i?'active':''}">
                            <a class="page-link" href="AdminViewAllPost?page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage==totalPages?'disabled':''}">
                        <a class="page-link" href="AdminViewAllPost?page=${currentPage+1}" aria-label="Next">&raquo;</a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <!-- Empty -->
        <c:if test="${empty posts}">
            <div class="alert alert-info text-center shadow-sm">
                No posts found.
            </div>
        </c:if>
    </div>
</main>

<jsp:include page="../guest/Footer.jsp" />
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
