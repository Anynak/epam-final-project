<%@ page import="com.epam.amazingServlet.domain.project.ProjectStatus" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2022
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="technicalTasks" scope="request" type="java.util.List"/>

<c:set var="statusValues" value="<%=ProjectStatus.values()%>"/>


<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>new project</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <p class="h4 mt-4 text-center">Create new project</p>
        <form action="setProject" method="post" class="border border-light p-1">

            <label for="projectName" class="form-label mt-5">Project description</label>
            <input type="text" placeholder="Project name" name="projectName" required id="projectName" class="form-control">

            <label for="technicalTaskId" class="form-label mt-5">Choose technical tas</label>
            <select name="technicalTaskId" id="technicalTaskId" class="form-select" >
                <c:forEach var="technicalTask" items= "${technicalTasks}">
                    <option value="${technicalTask.id}">${technicalTask.title}</option>
                </c:forEach>
            </select>
            <label for="projectStatus" class="form-label mt-5">Project status</label>
            <select name="projectStatus" id="projectStatus" class="form-select" >
                <c:forEach var="statusValue" items= "${statusValues}">
                    <option value="${statusValue}">${statusValue}</option>
                </c:forEach>
            </select>

            <button type="submit" class="btn btn-info btn-block my-4">ADD</button>

        </form>
    </div>
</div>
</body>
</html>
