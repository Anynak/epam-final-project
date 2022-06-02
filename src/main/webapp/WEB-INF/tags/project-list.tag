<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31.05.2022
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="projects" required="true" rtexprvalue="true" type="java.util.List" %>

<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <div class="list-group">
        <c:if test="${not empty projects}">
            <c:forEach var="project" items="${projects}">
                <a href="editProject?id=${project.id}" class="list-group-item list-group-item-action">${project.projectName}</a>
            </c:forEach>
        </c:if>
    </div>
</head>
<body>

</body>
</html>
