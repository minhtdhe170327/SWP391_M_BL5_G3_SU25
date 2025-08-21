<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>Forgot Password | HappyPrograming</title>
        <link rel="stylesheet" href="assets/css/stylelog.css"/>
        <style>
            /* Style cho nút Back to Login */
            .back-btn {
                margin-top: 25px;
                text-align: center;
            }

            .back-btn a {
                display: inline-block;
                background: #f1f1f1;
                color: #333;
                padding: 12px 50px;
                border-radius: 50px;
                font-size: 16px;
                font-weight: bold;
                text-decoration: none;
                transition: all 0.3s ease;
            }

            .back-btn a:hover {
                background: #333;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <nav class="nav">
                <div class="nav-logo">
                    <p>LOGO</p>
                </div>
                <div class="nav-menu" id="navMenu">
                    <ul>
                        <li><a href="home" class="link active">Home</a></li>
                    </ul>
                </div>
                <div class="nav-button">
                    <button class="btn white-button" id="loginBtn" onclick="navigateLogin()">
                        Sign in
                    </button>
                    <button class="btn" id="registerBtn"  onclick="navigateURL()">
                        Sign up
                    </button>
                </div>
                <div class="nav-menu-btn">
                    <i id="navMenuBtn" class="bx bx-menu" onclick="myMenuFunction()"></i>
                </div>
            </nav>

            <!-------------------- Form Box -------------------->
            <div class="form-box">
                <!--------------------  Success send email -------------------->
                <div class="forgot-container" id="ResetPassword">
                    <div class="top">
                        <header>Check your email</header>
                        <span>We've sent an email to your email</span>
                        <span>Please check your spam folder if you don't see the email in your box</span>
                    </div>

                    <!-- Nút Back to Login -->
                    <div class="back-btn">
                        <a href="<%=request.getContextPath()%>/Login">Back to Login</a>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function myMenuFunction() {
                var i = document.getElementById('navMenu');
                var menuBtn = document.getElementById('navMenuBtn');
                if (i.className === 'nav-menu') {
                    i.className += ' responsive';
                    menuBtn.className = 'bx bx-x';
                } else {
                    i.className = 'nav-menu';
                    menuBtn.className = 'bx bx-menu';
                }
            }

            function navigateLogin() {
                window.location.href = '<%=request.getContextPath()%>/Login';
            }

            function navigateURL() {
                window.location.href = '<%=request.getContextPath()%>/Register';
            }
        </script>
    </body>
</html>
