<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="assets/css/stylelog.css">
    <title>Register</title>
</head>
<body>
<div class="wrapper">
    <nav class="nav">
        <div class="nav-logo">
            <p>LOGO</p>
        </div>
        <div class="nav-menu" id="navMenu">
            <ul>
                <li><a href="ViewTop3Mentor" class="link active">Home</a></li>
            </ul>
        </div>
        <div class="nav-button">
            <button class="btn" id="loginBtn" onclick="window.location.href='Login'">Sign in</button>
            <button class="btn white-button" id="registerBtn" onclick="window.location.href='Register'">Sign up</button>
        </div>
        <div class="nav-menu-btn">
            <i id="navMenuBtn" class="bx bx-menu" onclick="myMenuFunction()"></i>
        </div>
    </nav>

    <!------------------------------------FORM BOX------------------------------------------->
    <div class="form-box">
        <!----------------------------------REGISTER------------------------------------------>
        <div class="register-container" id="register">
            <form action="Register" method="post">
                <div class="top">
                    <span>Have an account? <a href="<%=request.getContextPath()%>/Login">Sign in</a>
                    
                </div>
                    <header>SIGN UP</header>

                <!-- Firstname + Lastname -->
                <div class="two-forms">
                    <div class="input-box" style="width: 50%;">
                        <input type="text" name="firstname" class="input-field" placeholder="First name" required value="${param.firstname}">
                        <i class="bx bx-user"></i>
                    </div>
                    <div class="input-box" style="width: 50%;">
                        <input type="text" name="lastname" class="input-field" placeholder="Last name" required value="${param.lastname}">
                        <i class="bx bx-user"></i>
                    </div>
                </div>

                <!-- Role -->
                <div class="two-forms">
                    <div style="width: 50%;" class="input-box">
                        <select class="condition" name="role" required>
                            <option value="Mentee" ${param.role == 'Mentee' ? 'selected' : ''}>Mentee</option>
                            <option value="Mentor" ${param.role == 'Mentor' ? 'selected' : ''}>Mentor</option>
                        </select>
                        <i class="bx bx-id-card"></i>
                    </div>
                    <div style="width: 50%;" class="input-box">
                        <select class="condition" name="sex" required>
                            <option value="">Gender</option>
                            <option value="M" ${param.sex == 'M' ? 'selected' : ''}>Male</option>
                            <option value="F" ${param.sex == 'F' ? 'selected' : ''}>Female</option>
                        </select>
                        <i class="bx bx-male-female"></i>
                    </div>
                </div>

                <!-- Phone + Birthday -->
                <div class="two-forms">
                    <div class="input-box" style="width: 50%;">
                        <input type="text" name="phone" class="input-field" placeholder="Phone number" required value="${param.phone}">
                        <i class="bx bx-phone"></i>
                    </div>
                    <div class="input-box" style="width: 50%;">
                        <input type="date" id="dob" name="birthday" class="input-field" required value="${param.birthday}">
                        <i class="bx bx-calendar"></i>
                    </div>
                </div>

                <!-- Address -->
                <div class="input-box">
                    <input type="text" name="address" class="input-field" placeholder="Address" required value="${param.address}">
                    <i class="bx bx-home"></i>
                </div>

                <!-- Username -->
                <div class="input-box">
                    <c:set var="errorType1" value="${errorMsg.contains('username')}"/>
                    <input type="text" name="username" class="input-field ${errorType1 ? 'input-field-error' : ''}" placeholder="Username" required value="${param.newusername}">
                    <i class="bx bx-user"></i>
                </div>

                <!-- Password + Confirm -->
                <div class="two-forms">
                    <c:set var="errorType2" value="${errorMsg.contains('Password')}"/>
                    <div class="input-box" style="width: 50%;">
                        <input type="password" name="password" class="input-field ${errorType2 ? 'input-field-error' : ''}" placeholder="Password" required value="${param.newpassword}" pattern=".{6,}" title="at least 6 chars">
                        <i class="bx bx-lock-alt"></i>
                    </div>
                    <div class="input-box" style="width: 50%;">
                        <input type="password" name="confirmpass" class="input-field ${errorType2 ? 'input-field-error' : ''}" placeholder="Re-Password" required value="${param.repass}">
                        <i class="bx bx-lock-alt"></i>
                    </div>
                </div>

                <!-- Email -->
                <div class="input-box">
                    <c:set var="errorType3" value="${errorMsg.contains('email')}"/>
                    <input type="text" name="email" class="input-field ${errorType3 ? 'input-field-error' : ''}" placeholder="Email" required value="${param.newemail}">
                    <i class="bx bx-envelope"></i>
                </div>

                <!-- Error message -->
                <c:if test="${not empty errorMsg}">
                    <div class="errorMsg" style="color: red; background-color: rgba(255, 255, 255, 0.7); padding: 10px; margin-bottom: 20px; border-radius: 30px;">
                        ${errorMsg}
                    </div>
                </c:if>

                <!-- Submit -->
                <div class="input-box">
                    <input type="submit" class="submit" value="REGISTER"/>
                </div>

                <div class="two-col">
                    <div class="one"></div>
                    <div class="two">
                        <label><a href="#">Terms-Condition</a></label>
                    </div>
                </div>
            </form>
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

    // set max date cho ngày sinh
    const currentDate = new Date();
    const dateFormat = currentDate.toISOString().split('T')[0];
    document.getElementById('dob').setAttribute('max', dateFormat);
</script>
</body>
</html>
