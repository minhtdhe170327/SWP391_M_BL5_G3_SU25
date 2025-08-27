
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        .sidebar {
            background: #1abc9c;
            min-height: 100vh;
            padding-top: 30px;
        }
        .sidebar a {
            display: block;
            color: #fff;
            padding: 15px 20px;
            text-decoration: none;
            margin-bottom: 10px;
            border-radius: 5px;
            font-weight: 500;
            transition: background 0.2s;
        }
        .sidebar a.active, .sidebar a:hover {
            background: #16a085;
            color: #fff;
        }
        .sidebar h3 {
            color: #fff;
            margin-bottom: 40px;
            font-size: 1.3rem;
            letter-spacing: 1px;
        }
        .main-content {
            padding: 40px 30px;
            background: #f8f9fa;
            min-height: 100vh;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            margin: 30px 0;
        }
        @media (max-width: 768px) {
            .sidebar {
                min-height: auto;
                padding-top: 10px;
            }
            .main-content {
                padding: 15px 5px;
                margin: 10px 0;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="../guest/Header.jsp" />
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar">
                <h3 class="text-center mb-4">
                    <i class="bi bi-speedometer2"></i> Admin Panel
                </h3>
                <a href="ViewMenteeAccount"><i class="bi bi-person-plus"></i> Mentee Account List</a>
                <a href="ViewMentorAccount"><i class="bi bi-person-plus"></i> Mentor Account List</a>
                <a href="AdminViewAllRequest"><i class="bi bi-list-check"></i> Total Request</a>
                <a href="AdminViewAllHireRequest"><i class="bi bi-briefcase"></i> Total Hire Request</a>
                <a href="AdminViewAllPost"><i class="bi bi-file-earmark-text"></i> Total Post</a>
                <a href="AdminViewAllFeedback"><i class="bi bi-chat-dots"></i> Total Feedback</a>
                <a href="LogOut"><i class="bi bi-box-arrow-right"></i> Logout</a>
            </div>

            <!-- Main Content -->
            <div class="col-md-10 main-content">
                <h2 class="mb-4">Admin Dashboard</h2>
                <div class="row">
                    <!-- Total Request -->
                    <div class="col-md-2 mb-4">
                        <div class="card text-white bg-primary h-100">
                            <div class="card-body">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-list-check display-5 me-3"></i>
                                    <div>
                                        <h5 class="card-title">Total Request</h5>
                                        <p class="card-text fs-3">${totalRequest}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Total Hire Request -->
                    <div class="col-md-2 mb-4">
                        <div class="card text-white bg-success h-100">
                            <div class="card-body">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-briefcase display-5 me-3"></i>
                                    <div>
                                        <h5 class="card-title">Total Hire Request</h5>
                                        <p class="card-text fs-3">${totalHireRequest}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Total Post -->
                    <div class="col-md-2 mb-4">
                        <div class="card text-white bg-info h-100">
                            <div class="card-body">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-file-earmark-text display-5 me-3"></i>
                                    <div>
                                        <h5 class="card-title">Total Post</h5>
                                        <p class="card-text fs-3">${totalPost}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Total Feedback -->
                    <div class="col-md-2 mb-4">
                        <div class="card text-white bg-warning h-100">
                            <div class="card-body">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-chat-dots display-5 me-3"></i>
                                    <div>
                                        <h5 class="card-title">Total Feedback</h5>
                                        <p class="card-text fs-3">${totalFeedback}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Total Answer -->
                    <div class="col-md-2 mb-4">
                        <div class="card text-white bg-danger h-100">
                            <div class="card-body">
                                <div class="d-flex align-items-center">
                                    <i class="bi bi-pencil-square display-5 me-3"></i>
                                    <div>
                                        <h5 class="card-title">Total Answer</h5>
                                        <p class="card-text fs-3">${totalAnswer}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Chart -->
                <div class="row mt-5">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header bg-secondary text-white">
                                <i class="bi bi-bar-chart"></i> Thống kê tổng quan
                            </div>
                            <div class="card-body">
                                <canvas id="adminChart" height="80"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../guest/Footer.jsp" />
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const ctx = document.getElementById('adminChart').getContext('2d');
        const adminChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Total Request', 'Total Hire Request', 'Total Post', 'Total Feedback', 'Total Answer'],
                datasets: [{
                    label: 'Số lượng',
                    data: [
                        <c:out value="${totalRequest != null ? totalRequest : 0}"/>,
                        <c:out value="${totalHireRequest != null ? totalHireRequest : 0}"/>,
                        <c:out value="${totalPost != null ? totalPost : 0}"/>,
                        <c:out value="${totalFeedback != null ? totalFeedback : 0}"/>,
                        <c:out value="${totalAnswer != null ? totalAnswer : 0}"/>
                    ],
                    backgroundColor: [
                        'rgba(0, 123, 255, 0.7)',
                        'rgba(40, 167, 69, 0.7)',
                        'rgba(23, 162, 184, 0.7)',
                        'rgba(255, 193, 7, 0.7)',
                        'rgba(220, 53, 69, 0.7)'
                    ],
                    borderColor: [
                        'rgba(0, 123, 255, 1)',
                        'rgba(40, 167, 69, 1)',
                        'rgba(23, 162, 184, 1)',
                        'rgba(255, 193, 7, 1)',
                        'rgba(220, 53, 69, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: { legend: { display: false } },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: { stepSize: 1 }
                    }
                }
            }
        });
    </script>
</body>
</html>
