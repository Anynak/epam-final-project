<%@ page import="com.epam.amazingServlet.domain.user.Qualification" %>
<%@ page import="com.epam.amazingServlet.domain.user.Position" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.Developer"/>
<c:set var="qualifications" value="<%=Qualification.values()%>"/>
<c:set var="position" value="<%=Position.values()%>"/>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>edit profile</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <p class="h4 mb-4 text-center">Edit profile</p>
        <form action="editProfile" method="post">

            Qualification
            <select name="qualification" class="form-select mb-3" >
                <c:forEach var="qualification" items= "${qualifications}">
                    <c:if test="${user.qualification==qualification}">
                        <option selected value="${qualification}">${qualification.value}</option>
                    </c:if>
                    <c:if test="${user.qualification!=qualification}">
                        <option value="${qualification}">${qualification.value}</option>
                    </c:if>
                </c:forEach>
            </select>

            <u:user-form user="${user}"/>

            <div class="container signin">
                <p><a href="logout">Log out</a>.</p>
            </div>
            <input type="hidden" name="position" value="${Position.DEVELOPER}">
        </form>
    </div>
</div>

</body>
</html>
