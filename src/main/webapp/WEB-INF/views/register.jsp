<%@ page import="com.epam.amazingServlet.domain.user.Position" %><%--
  Created by IntelliJ IDEA.
  User: Anynak
  Date: 15.05.2022
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<c:set var="positions" value="<%=Position.values()%>"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>sign up</title>
</head>
<body>
<br/>

<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <p class="h4 mb-4 text-center">Registration</p>
        <form action="register" method="post" class="border border-light p-5">
            Choose your position<br/>
            <select name="position" class="form-select" >
                <c:forEach var="position" items= "${positions}">
                    <option value="${position}">${position.value}</option>
                </c:forEach>
            </select>
            <br>
            <u:user-form user="${null}"/>
            <div class="container signin">
                <p>Already have an account? <a href="auth">Sign in</a>.</p>
            </div>
        </form>
        <div>
            <p class="text-center">
                Already have an account? <a href="auth">Sign in</a>.
            </p>

        </div>
    </div>
</div>


</body>
</html>
