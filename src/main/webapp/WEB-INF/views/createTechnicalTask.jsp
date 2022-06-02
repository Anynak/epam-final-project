<%--
  Created by IntelliJ IDEA.
  User: Demiurge
  Date: 26.05.2022
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>new technical task</title>
</head>
<body>
<form action="addNewTechnicalTask" method="post">
    <div class="container">
        <h1>new task name</h1>
        <p>technical task name</p>
        <br>
        <input type="text" placeholder="technical task name" name="technicalTaskName" required>
        <br><br>
        <button type="submit">Next</button>
    </div>
</form>
</body>
</html>
