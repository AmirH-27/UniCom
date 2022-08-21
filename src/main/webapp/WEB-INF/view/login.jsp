<%--
  Created by IntelliJ IDEA.
  User: jubayer
  Date: 21/8/22
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
<%--    <div th:if="${isCheck}"><title>Welcome to UniCom</title></div>--%>
<%--    <div th:if="${isRegister}"><title>Register</title></div>--%>
<%--    <div th:if="${isLogin}"><title>Login</title></div>--%>
</head>
<body>
<div ${not isCheck} ? ('hidden')>
    <h1>Welcome to UniCom</h1>
    <h1>${view}</h1>
    <form action="${pageContext.request.contextPath}/check" method="post" th:object="${user}">
        <div>
            <input type="text" th:field="*{studentID}" placeholder="Student ID"/><br><br>
        </div>
        <input type="submit" value="Next" />
    </form>
</div>
    <div ${not isRegister} ? ('hidden')>
    <h1>Registration</h1>
    <form action="${pageContext.request.contextPath}/register" method="post" th:object="${user}">
        <div>
            <input type="text" th:field="*{studentID}" placeholder="Student ID"/><br><br>
            <input type="text" th:field="*{userName}" placeholder="Full Name"/><br><br>
            <input type="text" th:field="*{userEmail}" placeholder="Student Email"/><br><br>
            <input type="password" th:field="*{userPass}" placeholder="Password"/><br><br>
        </div>
        <input type="submit" value="Registration" />
    </form>
    </div>
    <div (${not isRegister} ? hidden )>
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post" th:object="${user}">
        <div>
            <input type="text" th:field="*{studentID}" placeholder="Student ID"/><br><br>
            <input type="password" th:field="*{userPass}" placeholder="Password"/><br><br>
        </div>
        <input type="submit" value="Login" />
    </form>
    </div>
<%--<div th:if="${param.error}">--%>
<%--    Invalid username and password.</div>--%>
<%--<div th:if="${param.logout}">--%>
<%--    You have been logged out.</div>--%>
<%--<form th:action="@{/login}" method="post">--%>
<%--    <div>--%>
<%--        <input type="text" name="username" placeholder="Username"/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <input type="password" name="password" placeholder="Password"/>--%>
<%--    </div>--%>
<%--    <input type="submit" value="Log in" />--%>
<%--</form>--%>
</body>
</html>
