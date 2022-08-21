<%--
  Created by IntelliJ IDEA.
  User: jubayer
  Date: 21/8/22
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
<head>
    <div th:if="${param.isCheck}"><title>Welcome to UniCom</title></div>
    <div th:else if="${param.isRegister}"><title>Register</title></div>
    <div th:else><title>Login</title></div>
</head>
<body>
<div th:if="${param.isCheck}">
    <h>Welcome to UniCom</h>
    <form th:action="@{/login/check}" method="post">
        <div>
            <input type="text" name="studentId" placeholder="Student ID"/>
        </div>
        <input type="submit" value="Next" />
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
