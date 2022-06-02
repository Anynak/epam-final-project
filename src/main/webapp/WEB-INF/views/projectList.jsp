<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2022
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="projects" scope="request" type="java.util.List"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>Projects</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <p class="h4 mb-4 mt-5 text-center">Projects you supervise</p>
        <u:project-list projects="${projects}"/>
        <p class="text-right mt-2">
            <a href="setProject">Add new project</a>.
        </p>
        <a href="editProfile">edit profile</a>
    </div>
</div>
</body>
</html>
