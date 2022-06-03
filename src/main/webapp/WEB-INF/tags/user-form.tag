<%@ attribute name="user" required="false" rtexprvalue="true" type="com.epam.amazingServlet.domain.user.User" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename = "resource" var = "mess"/>

<fmt:message key="email" var="email" bundle = "${mess}"/>
<fmt:message key="first_name" var="first_name" bundle = "${mess}"/>
<fmt:message key="last_name" var="last_name" bundle = "${mess}"/>
<fmt:message key="password" var="password" bundle = "${mess}"/>
<fmt:message key="repeat_password" var="repeat_password" bundle = "${mess}"/>

<fmt:message key="Enter_Email" var="Enter_Email" bundle = "${mess}"/>
<fmt:message key="Enter_F_Name" var="Enter_F_Name" bundle = "${mess}"/>
<fmt:message key="Enter_L_Name" var="Enter_L_Name" bundle = "${mess}"/>
<fmt:message key="Enter_Password" var="Enter_Password" bundle = "${mess}"/>
<fmt:message key="register" var="register" bundle = "${mess}"/>
<fmt:message key="to_register" var="to_register" bundle = "${mess}"/>
<html>
<head>
</head>
<body>

<c:if test="${not empty user}">
    <jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>
    ${email}
    <br>
    <input type="text" name="email" value="${user.email}" required id="defaultLoginFormEmail" class="form-control">
    <br>
   ${first_name}
    <br>
    <input type="text" value="${user.firstName}" name="firstName" required id="textInput" class="form-control">
    <br>
    ${last_name}
    <br>
    <input type="text" value="${user.lastName}" name="lastName" required id="textInput" class="form-control">
    <br>

    ${password}
    <br>
    <input type="password" placeholder="${Enter_Password}" name="psw" required id="defaultLoginFormPassword"
           class="form-control">
    <br>
    ${repeat_password}
    <br>
    <input type="password" placeholder="${repeat_password}" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control">

    <button type="submit" class="btn btn-info btn-block my-4">${to_register}</button>

</c:if>
<c:if test="${empty user}">


    ${email}
    <br>
    <input type="text" name="email" placeholder="${Enter_Email}" required id="defaultLoginFormEmail" class="form-control">
    <br>
    ${first_name}
    <br>
    <input type="text" placeholder="${Enter_F_Name}" name="firstName" required id="textInput" class="form-control">
    <br>
    ${last_name}
    <br>
    <input type="text" placeholder="${Enter_L_Name}" name="lastName" required id="textInput" class="form-control">
    <br>

    ${password}
    <br>
    <input type="password" placeholder="${Enter_Password}" name="psw" required id="defaultLoginFormPassword"
           class="form-control">
    <br>
    ${repeat_password}
    <br>
    <input type="password" placeholder="${repeat_password}" name="psw-repeat" required id="defaultLoginFormPassword"
           class="form-control">

    <button type="submit" class="btn btn-info btn-block my-4">${to_register}</button>
</c:if>
</body>


</html>
