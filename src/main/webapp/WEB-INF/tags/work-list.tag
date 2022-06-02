<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2022
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="works" required="true" rtexprvalue="true" type="java.util.List" %>

<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="list-group">
    <p class="mt-4 text-center">List of work in the task</p>
    <c:if test="${not empty works}">
        <c:forEach var="work" items="${works}">
            <c:if test="${work.finishStatus==true}">
                <a href="work?Id=${work.id}" class="list-group-item list-group-item-action list-group-item-success">${work.description}</a>
            </c:if>
            <c:if test="${work.finishStatus==false}">
                <a href="work?Id=${work.id}" class="list-group-item list-group-item-action list-group-item-warning">${work.description}</a>
            </c:if>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
