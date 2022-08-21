<%--
  Created by IntelliJ IDEA.
  User: jubayer
  Date: 21/8/22
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Welcome To UniCom</title>
    <c: if(${isCheck}){ />
        <title>Welcome To UniCom</title>
    <c: } />
    <c: else if(${isRegister}){ />
        <title>Register</title>
    <c: } />
    <c: else{ />
        <title>Login</title>
    <c: } />
</head>
<body>
    <c: if(${isWrongAuth}){ />
        <h1>Wrong Username or Password</h1>
    <c: } />
    <c: if(${isCheck}){ />
        <h>Welcome To UniCom</h>
        <form:form action="login/check" method="post" modelAttribute="user">
        Student ID: <form:input path="studentID"/>
        <form:errors path="studentID" /><br/><br/>
        <input type="submit" value="Next">
        </form:form>
    <c: } />
    <c: else if(${isRegister}){ />
        <h>Register</h>
        <form:form action="register" method="post" modelAttribute="user">
        Student ID: <form:input path="studentID1"/>
        <form:errors path="studentID" /><br/><br/>
        <input type="submit" value="Register">
        </form:form>
    <c: } />
    <c: else{ />
        <h>Login</h>
        <form:form action="login" method="post" modelAttribute="user">
        Student ID: <form:input path="studentID2"/>
        <form:errors path="studentID" /><br/><br/>
        Password: <form:input path="userPass"/>
        <form:errors path="userPass" /><br/><br/>
        <input type="submit" value="Login">
        </form:form>
    <c: } />
</body>
</html>
