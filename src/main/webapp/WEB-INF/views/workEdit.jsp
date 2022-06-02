<%--
  Created by IntelliJ IDEA.
  User: Anynak
  Date: 30.05.2022
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>work edit</title>
</head>
<body>
<jsp:useBean id="work" scope="request" type="com.epam.amazingServlet.domain.project.Work"/>
<p class="h4 mt-4 text-center">Edit your work</p>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <form action="setWork" method="post" class="border border-light p-2">
            <u:work-form  work="${work}"/>
            <input type="hidden" name="technicalTaskId" value="${work.technicalTaskId}">
            <input type="hidden" name="workId" value="${work.id}">
            <button class="btn btn-info btn-block my-4" type="submit">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
