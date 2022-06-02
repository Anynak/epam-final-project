<%@ page import="com.epam.amazingServlet.domain.user.Qualification" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.05.2022
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="works" scope="request" type="java.util.List"/>
<jsp:useBean id="technicalTask" scope="request" type="com.epam.amazingServlet.domain.project.TechnicalTask"/>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">

        <p class="h4 mt-4 text-center">Edit your technical task<br>"${technicalTask.title}"</p>
        <u:work-list works="${works}"/>
        <p class="mt-4 text-center">Add new work</p>
        <form action="setWork" method="post" class="border border-light p-2">
            <div class="mb-3">
                <u:work-form  work="${null}"/>
                <input type="hidden" name="technicalTaskId" value="${technicalTask.id}">
                <button class="btn btn-info btn-block my-4" type="submit">Add</button>
            </div>
        </form>
        <br>

    </div>
</div>
</body>
</html>
