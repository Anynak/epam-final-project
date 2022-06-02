<%--
  Created by IntelliJ IDEA.
  User: Anynak
  Date: 29.05.2022
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="technicalTasks" scope="request" type="java.util.List"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <p class="h4 mb-4 text-center">Technical tasks you set</p>
        <u:Technical-task-list technicalTasks="${technicalTasks}"/>
        <form action="addTechnicalTask" method="post" class="border border-light p-2 row">
            <label for="textInput" class="form-label mt-5">Add technicalTask</label>
            <input type="text" placeholder="Title" name="taskTitle" required id="textInput" class="form-control">
            <button type="submit" class="btn btn-info btn-block my-4">ADD</button>
        </form>

        <br>
        <a href="editProfile">edit profile</a>
    </div>
</div>

</body>
</html>
