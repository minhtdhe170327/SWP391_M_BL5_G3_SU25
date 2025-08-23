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
                    <button 
                        class="btn white-button" 
                        id="loginBtn" 
                        onclick="window.location.href = '<%=request.getContextPath()%>/Login'">
                        Sign in
                    </button>
                    <button 
                        class="btn white-button" 
                        id="loginBtn" 
                        onclick="window.location.href = '<%=request.getContextPath()%>/Register'">
                        Sign up
                    </button>
                </div>
                <div class="nav-menu-btn">
                    <i id="navMenuBtn" class="bx bx-menu" onclick="myMenuFunction()"></i>
                </div>
            </nav>

            <!-------------------- Form Box -------------------->
            <div class="form-box">
                <c:choose>
                    <c:when test="${emailExisted eq false}">
                        <!-------------------- Forgot Password -------------------->
                        <div class="forgot-container" id="ResetPassword">
                            <form action="ResetPassword" method="post">
                                <div class="top">
                                    <header>Reset your password</header>
                                    <span>Enter your email address you signed up with. We'll email you a temp password and link to change your password</span>
                                </div>
                                <div class="top" style="color: red">
                                    <span>${err}</span>
                                </div>
                                <div class="input-box">
                                    <input type="text" name="email" class="input-field" placeholder="Email address" value="">
                                    <i class="bx bx-envelope"></i>
                                </div>
                                <div class="input-box forgot-btn">
                                    <input type="submit" class="submit" value="Submit">
                                </div>
                            </form>
                        </div>                        
                    </c:when>
                    <c:when test="${emailExisted eq true}">
                        <!--------------------  Sucess send email -------------------->
                        <div class="forgot-container" id="ResetPassword">
                            <div class="top">
                                <header>Check your email</header>
                                <span>We've sent an email to your email</span>
                                <span>Please check your spam folder if you don't see the email in your box</span>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>
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

            function navigateURL() {
                "window.location.href = '<%=request.getContextPath()%>/Login'";
            }
        </script>
    </body>
</html>
