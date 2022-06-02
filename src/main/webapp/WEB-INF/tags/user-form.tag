<%@ attribute name="user" required="false" rtexprvalue="true" type="com.epam.amazingServlet.domain.user.User" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<c:if test="${not empty user}">
    <jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>

    Email
    <br>
    <input type="text" name="email" value="${user.email}" required id="defaultLoginFormEmail" class="form-control">
    <br>
    First name
    <br>
    <input type="text" value="${user.firstName}" name="firstName" required id="textInput" class="form-control">
    <br>

    Last name
    <br>
    <input type="text" value="${user.lastName}" name="lastName" required id="textInput" class="form-control">
    <br>

    Password
    <br>
    <input type="password" placeholder="Enter Password" name="psw" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">
    <br>
    Repeat Password
    <br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">

    <button type="submit" class="btn btn-info btn-block my-4">Submit</button>

</c:if>
<c:if test="${empty user}">


    Email
    <br>
    <input type="text" placeholder="Enter Email" name="email" required id="defaultLoginFormEmail" class="form-control">
    <br>
    First name
    <br>
    <input type="text" placeholder="Enter First name" name="firstName" required id="textInput" class="form-control">
    <br>

    Last name
    <br>
    <input type="text" placeholder="Enter Last name" name="lastName" required id="textInput" class="form-control">
    <br>

    Password
    <br>
    <input type="password" placeholder="Enter Password" name="psw" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">
    <br>
    Repeat Password
    <br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">

    <button type="submit" class="btn btn-info btn-block my-4">Register</button>
</c:if>
</body>


</html>
