<%--
  Created by IntelliJ IDEA.
  User: jubayer
  Date: 21/8/22
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Poppins');

        /* BASIC */
        fieldset {
            border: 0;
        }

        html {
            background-color: #abdbe3;
        }
        img{
            border-radius: 50%;
        }
        body {
            font-family: "Poppins", sans-serif;
            height: 100vh;
        }

        a {
            color: #AEC7BA;
            display:inline-block;
            text-decoration: none;
            font-weight: 400;
        }

        h2 {
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            text-transform: uppercase;
            display:inline-block;
            margin: 40px 8px 10px 8px;
            color: #cccccc;
        }



        /* STRUCTURE */

        .wrapper {
            display: flex;
            align-items: center;
            flex-direction: column;
            justify-content: center;
            width: 100%;
            min-height: 100%;
            padding: 20px;
        }

        #formContent {
            -webkit-border-radius: 10px 10px 10px 10px;
            border-radius: 10px 10px 10px 10px;
            background: #FFFFFF;
            padding: 30px;
            width: 90%;
            max-width: 450px;
            position: relative;
            padding: 0px;
            -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
            box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
            text-align: center;
        }
        #formFooter {
            background-color: #f6f6f6;
            border-top: 1px solid #dce8f1;
            padding: 25px;
            text-align: center;
            -webkit-border-radius: 0 0 10px 10px;
            border-radius: 0 0 10px 10px;
        }
        /* TABS */

        h2.inactive {
            color: #cccccc;
        }

        h2.active {
            color: #0d0d0d;
            border-bottom: 2px solid #5fbae9;
        }



        /* FORM TYPOGRAPHY*/

        input[type=button], input[type=submit], input[type=reset]  {
            background-color: #5FADB4;
            border: none;
            color: white;
            padding: 15px 80px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            text-transform: uppercase;
            font-size: 13px;
            -webkit-box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
            box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
            -webkit-border-radius: 5px 5px 5px 5px;
            border-radius: 5px 5px 5px 5px;
            margin: 5px 20px 40px 20px;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -ms-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }

        input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {
            background-color: #33cccc;
        }

        input[type=button]:active, input[type=submit]:active, input[type=reset]:active  {
            -moz-transform: scale(0.95);
            -webkit-transform: scale(0.95);
            -o-transform: scale(0.95);
            -ms-transform: scale(0.95);
            transform: scale(0.95);
        }

        input[type=text] {
            background-color: #f6f6f6;
            border: none;
            color: #0d0d0d;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 5px;
            width: 85%;
            border: 2px solid #f6f6f6;
            -webkit-transition: all 0.5s ease-in-out;
            -moz-transition: all 0.5s ease-in-out;
            -ms-transition: all 0.5s ease-in-out;
            -o-transition: all 0.5s ease-in-out;
            transition: all 0.5s ease-in-out;
            -webkit-border-radius: 5px 5px 5px 5px;
            border-radius: 5px 5px 5px 5px;
        }

        input[type=text]:focus {
            background-color: #fff;
            border-bottom: 2px solid #AEC7BA;
        }

        input[type=text]::placeholder {
            color: #cccccc;
        }



        input[type=password] {
            background-color: #f6f6f6;
            border: none;
            color: #0d0d0d;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 5px;
            width: 85%;
            border: 2px solid #f6f6f6;
            -webkit-transition: all 0.5s ease-in-out;
            -moz-transition: all 0.5s ease-in-out;
            -ms-transition: all 0.5s ease-in-out;
            -o-transition: all 0.5s ease-in-out;
            transition: all 0.5s ease-in-out;
            -webkit-border-radius: 5px 5px 5px 5px;
            border-radius: 5px 5px 5px 5px;
        }

        input[type=password]:focus {
            background-color: #fff;
            border-bottom: 2px solid #AEC7BA;
        }

        input[type=password]::placeholder {
            color: #cccccc;
        }
        /* ANIMATIONS */
        /* Simple CSS3 Fade-in-down Animation */
        .fadeInDown {
            -webkit-animation-name: fadeInDown;
            animation-name: fadeInDown;
            -webkit-animation-duration: 1s;
            animation-duration: 1s;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }

        .hide{
            display: none;
        }

        @-webkit-keyframes fadeInDown {
            0% {
                opacity: 0;
                -webkit-transform: translate3d(0, -100%, 0);
                transform: translate3d(0, -100%, 0);
            }
            100% {
                opacity: 1;
                -webkit-transform: none;
                transform: none;
            }
        }

        @keyframes fadeInDown {
            0% {
                opacity: 0;
                -webkit-transform: translate3d(0, -100%, 0);
                transform: translate3d(0, -100%, 0);
            }
            100% {
                opacity: 1;
                -webkit-transform: none;
                transform: none;
            }
        }

        /* Simple CSS3 Fade-in Animation */
        @-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
        @-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
        @keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

        .fadeIn {
            opacity:0;
            -webkit-animation:fadeIn ease-in 1;
            -moz-animation:fadeIn ease-in 1;
            animation:fadeIn ease-in 1;

            -webkit-animation-fill-mode:forwards;
            -moz-animation-fill-mode:forwards;
            animation-fill-mode:forwards;

            -webkit-animation-duration:1s;
            -moz-animation-duration:1s;
            animation-duration:1s;
        }

        .fadeIn.first {
            -webkit-animation-delay: 0.4s;
            -moz-animation-delay: 0.4s;
            animation-delay: 0.4s;
        }

        .fadeIn.second {
            -webkit-animation-delay: 0.6s;
            -moz-animation-delay: 0.6s;
            animation-delay: 0.6s;
        }

        .fadeIn.third {
            -webkit-animation-delay: 0.8s;
            -moz-animation-delay: 0.8s;
            animation-delay: 0.8s;
        }
        .fadeIn.fourth {
            -webkit-animation-delay: 1s;
            -moz-animation-delay: 1s;
            animation-delay: 1s;
        }
        /* Simple CSS3 Fade-in Animation */
        .underlineHover:after {
            display: block;
            left: 0;
            bottom: -10px;
            width: 0;
            height: 2px;
            background-color: #56baed;
            content: "";
            transition: width 0.2s;
        }
        .underlineHover:hover {
            color: #0d0d0d;
        }
        .underlineHover:hover:after{
            width: 100%;
        }
        /* OTHERS */
        *:focus {
            outline: none;
        }
        #icon {
            width:60%;
        }
        * {
            box-sizing: border-box;
        }
        .error{
            color: #c73e1d;
        }
    </style>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id = "formContent">
        <img src="https://img.freepik.com/free-vector/young-people-walking-front-college-university-flat-illustration_74855-14224.jpg?w=996&t=st=1661192536~exp=1661193136~hmac=0ad10a34aa17fe53efa766438bf8014107f8071c0adb01395db247c9e44647a2" id="icon" alt="User Icon" width="300" height="200"/>
        <h1>Welcome to UniCom</h1>

        <div ${isCheck}>
            <form action="${pageContext.request.contextPath}/check" method="post" th:object="${user}">
                <div>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}"/>
                    <div class="error">${studentIDError}</div><br/>
                </div>
                <input type="submit" value="Next" />
            </form>
        </div>
        <div ${isRegister}>
            <h1>Registration</h1>
            <form action="${pageContext.request.contextPath}/register" method="post" th:object="${user}">
                <div>
                    <div class="hide">
                        <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" hidden/>
                    </div>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" disabled/>
                    <div class="error">${studentIDError}</div><br/>
                    <input type="text" name="userName" th:field="*{userName}" placeholder="Full Name" value="${user.userName}"/>
                    <div class="error">${userNameError}</div><br/>
                    <input type="password" name="userPass" th:field="*{userPass}" placeholder="Password" value="${user.userPass}"/>
                    <div class="error">${userPassError}</div><br/>
                    <input type="password" name="confirmUserPass" th:field="*{confirmUserPass}" placeholder="Confirm Password"/>
                    <div class="error">${confirmUserPassError}</div><br/>
                    <input type="radio" name="public" th:field="*{public}" value="false" checked>Private
                    <input type="radio" name="public" th:field="*{public}" value="true">Public
                    <br/>
                </div><br/>
                <input type="submit" value="Registration" />
            </form>
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Back" />
            </form>
        </div>
        <div ${isLogin}>
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/signing" method="post" th:object="${user}">
                <div>
                    <div  class="hide">
                        <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" />
                    </div>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" disabled/>
                    <div class="error">${studentIDError}</div><br/>
                    <input type="password" name="userPass" th:field="*{userPass}" placeholder="Password" value="${user.userPass}"/>
                    <div class="error">${userPassError}</div><br/>
                    <input type="checkbox" name="rememberMe">Remember Me
                </div><br/>
                <input type="submit" value="Login" />
            </form>
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Back" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
