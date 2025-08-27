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
        a.card-link {
            text-decoration: none;
        }
        .card {
            min-height: 150px; /* cao bằng nhau */
            display: flex;
            align-items: center;
        }
        .card:hover {
            transform: scale(1.03);
            transition: 0.2s;
            cursor: pointer;
        }
    </style>
</head>
<body>
    
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar">
                <h3 class="text-center mb-4">
                    <i class="bi bi-speedometer2"></i> Admin Panel
                </h3>
                <a href="ViewMenteeAccount"><i class="bi bi-person-lines-fill"></i> Account Mentee List</a>
                <a href="ViewMentorAccount"><i class="bi bi-person-badge-fill"></i> Account Mentor List</a>
                <a href="AdminViewAllRequest"><i class="bi bi-list-check"></i> Total Request</a>
                <a href="AdminViewAllHireRequest"><i class="bi bi-briefcase-fill"></i> Total Hire Request</a>
                <a href="AdminViewAllPost"><i class="bi bi-file-earmark-text-fill"></i> Total Post</a>
                <a href="AdminViewAllFeedback"><i class="bi bi-chat-dots-fill"></i> Total Feedback</a>
                <a href="AdminSkillManager"><i class="bi bi-lightning-charge-fill"></i> Skill Manager</a>
                <a href="JobManager"><i class="bi bi-briefcase"></i> Job Manager</a>
                <a href="LogOut"><i class="bi bi-box-arrow-right"></i> Logout</a>
            </div>

            <!-- Main Content -->
            <div class="col-md-10 main-content">
                <h2 class="mb-4">Welcome to Admin Dashboard</h2>

                <!-- Row 1: Total -->
                <div class="row">
                    <div class="col-md-3 mb-4">
                        <a href="AdminViewAllRequest" class="card-link">
                            <div class="card text-white bg-primary h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-list-check display-5 me-3"></i>
                                    <div>
                                        <h5>Total Request</h5>
                                        <p class="fs-3">${totalRequest}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 mb-4">
                        <a href="AdminViewAllHireRequest" class="card-link">
                            <div class="card text-white bg-success h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-briefcase display-5 me-3"></i>
                                    <div>
                                        <h5>Total Hire Request</h5>
                                        <p class="fs-3">${totalHireRequest}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 mb-4">
                        <a href="AdminViewAllPost" class="card-link">
                            <div class="card text-white bg-info h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-file-earmark-text display-5 me-3"></i>
                                    <div>
                                        <h5>Total Post</h5>
                                        <p class="fs-3">${totalPost}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 mb-4">
                        <a href="AdminViewAllFeedback" class="card-link">
                            <div class="card text-white bg-warning h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-chat-dots display-5 me-3"></i>
                                    <div>
                                        <h5>Total Feedback</h5>
                                        <p class="fs-3">${totalFeedback}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3 mb-4">
                        <a href="AdminViewAllAnswer" class="card-link">
                            <div class="card text-white bg-danger h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-pencil-square display-5 me-3"></i>
                                    <div>
                                        <h5>Total Answer</h5>
                                        <p class="fs-3">${totalAnswer}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

                <!-- Row 2: Manager -->
                <div class="row">
                    <div class="col-md-3 mb-4">
                        <a href="JobManager" class="card-link">
                            <div class="card text-white h-100" style="background:#20c997;">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-briefcase-fill display-5 me-3"></i>
                                    <div>
                                        <h5>Job Manager</h5>
                                        <p class="fs-3">${totalJob}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 mb-4">
                        <a href="AdminSkillManager" class="card-link">
                            <div class="card text-white h-100" style="background:#6f42c1;">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-lightning-charge-fill display-5 me-3"></i>
                                    <div>
                                        <h5>Skill Manager</h5>
                                        <p class="fs-3">${totalSkill}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

                <!-- Row 3: Account -->
                <div class="row">
                    <div class="col-md-3 mb-4">
                        <a href="ViewMenteeAccount" class="card-link">
                            <div class="card text-white bg-secondary h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-person-lines-fill display-5 me-3"></i>
                                    <div>
                                        <h5>Account Mentee</h5>
                                        <p class="fs-3">${totalMentee}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-md-3 mb-4">
                        <a href="ViewMentorAccount" class="card-link">
                            <div class="card text-white bg-dark h-100">
                                <div class="card-body d-flex align-items-center">
                                    <i class="bi bi-person-badge-fill display-5 me-3"></i>
                                    <div>
                                        <h5>Account Mentor</h5>
                                        <p class="fs-3">${totalMentor}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="../guest/Footer.jsp" />
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
