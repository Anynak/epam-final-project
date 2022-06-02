<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="technicalTasks" required="true" rtexprvalue="true" type="java.util.List" %>

<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Tasks</title>
</head>
<body>
<div class="list-group">
    <c:if test="${not empty technicalTasks}">
        <c:forEach var="task" items="${technicalTasks}">
            <a href="technicalTask?id=${task.id}" class="list-group-item list-group-item-action">${task.title}</a>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
