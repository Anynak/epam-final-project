<%@ attribute name="user" required="false" rtexprvalue="true" type="com.epam.amazingServlet.domain.user.User" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename = "resource" var = "mess"/>
<html>
<head>
</head>
<body>
<c:if test="${not empty user}">
    <jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>
    <fmt:message key="email" bundle = "${mess}"/>
    <br>
    <input type="text" name="email" value="${user.email}" required id="defaultLoginFormEmail" class="form-control">
    <br>
    <fmt:message key="first_name" bundle = "${mess}"/>
    <br>
    <input type="text" value="${user.firstName}" name="firstName" required id="textInput" class="form-control">
    <br>
    <fmt:message key="last_name" bundle = "${mess}"/>
    <br>
    <input type="text" value="${user.lastName}" name="lastName" required id="textInput" class="form-control">
    <br>

    <fmt:message key="password" bundle = "${mess}"/>
    <br>
    <input type="password" placeholder="Enter Password" name="psw" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">
    <br>
    <fmt:message key="repeat_password" bundle = "${mess}"/>
    <br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">

    <button type="submit" class="btn btn-info btn-block my-4">Submit</button>

</c:if>
<c:if test="${empty user}">


    <fmt:message key="email" bundle = "${mess}"/>
    <br>
    <input type="text" placeholder="Enter Email" name="email" required id="defaultLoginFormEmail" class="form-control">
    <br>
    <fmt:message key="first_name" bundle = "${mess}"/>
    <br>
    <input type="text" placeholder="Enter First name" name="firstName" required id="textInput" class="form-control">
    <br>

    <fmt:message key="last_name" bundle = "${mess}"/>
    <br>
    <input type="text" placeholder="Enter Last name" name="lastName" required id="textInput" class="form-control">
    <br>

    <fmt:message key="password" bundle = "${mess}"/>
    <br>
    <input type="password" placeholder="Enter Password" name="psw" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">
    <br>
    <fmt:message key="repeat_password" bundle = "${mess}"/>
    <br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control" placeholder="Password">

    <button type="submit" class="btn btn-info btn-block my-4">Register</button>
</c:if>
</body>


</html>
