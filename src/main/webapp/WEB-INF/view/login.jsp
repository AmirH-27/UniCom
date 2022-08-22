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
        <title>Welcome</title>
    </head>
    <body>
        <div ${isCheck}>
            <h1>Welcome to UniCom</h1>
            <form action="${pageContext.request.contextPath}/check" method="post" th:object="${user}">
               <div>
                   <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}"/>
                    <div>${studentIDError}</div><br/>
               </div>
               <input type="submit" value="Next" />
            </form>
        </div>
        <div ${isRegister}>
            <h1>Registration</h1>
            <form action="${pageContext.request.contextPath}/register" method="post" th:object="${user}">
                <div>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" hidden/>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" disabled/>
                    <div>${studentIDError}</div><br/>
                    <input type="text" name="userName" th:field="*{userName}" placeholder="Full Name" value="${user.userName}"/>
                    <div>${userNameError}</div><br/>
                    <input type="password" name="userPass" th:field="*{userPass}" placeholder="Password" value="${user.userPass}"/>
                    <div>${userPassError}</div><br/>
                    <input type="password" name="confirmUserPass" th:field="*{confirmUserPass}" placeholder="Confirm Password""/>
                    <div>${confirmUserPassError}</div><br/>
                    <input type="radio" name="public" th:field="*{public}" value="false" checked>Private
                    <input type="radio" name="public" th:field="*{public}" value="true">Public
                    <br/>
                </div><br/>
                <input type="submit" value="Registration" />
            </form><br/>
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Back" />
            </form>
        </div>
        <div ${isLogin}>
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/signing" method="post" th:object="${user}">
                <div>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" hidden/>
                    <input type="text" name="studentID" th:field="*{studentID}" placeholder="Student ID" value="${user.studentID}" disabled/>
                    <div>${studentIDError}</div><br/>
                    <input type="password" name="userPass" th:field="*{userPass}" placeholder="Password" value="${user.userPass}"/>
                    <div>${userPassError}</div><br/>
                    <input type="checkbox" name="rememberMe">Remember Me
                </div><br/>
                <input type="submit" value="Login" />
            </form><br/>
            <form action="${pageContext.request.contextPath}/login" method="get">
                <input type="submit" value="Back" />
            </form>
        </div>
    </body>
</html>
