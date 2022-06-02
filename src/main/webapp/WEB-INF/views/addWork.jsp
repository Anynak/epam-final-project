<jsp:useBean id="work" scope="request" type="com.epam.amazingServlet.domain.project.Work"/>
<jsp:useBean id="technicalTask" scope="request" type="com.epam.amazingServlet.domain.project.TechnicalTask"/>
<jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>
<jsp:useBean id="qualification" scope="request" type="com.epam.amazingServlet.domain.user.Qualification"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Demiurge
  Date: 27.05.2022
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${technicalTask}

<jsp:useBean id="works" scope="request" type="java.util.List"/>
<c:forEach var="work" items="${works}">
    ${work.description}
</c:forEach>

<form action="addNewWork" method="post">
    <div class="container">
        <h1>new work</h1>
        <p>work description</p>
        <br>
        <input type="text" placeholder="work description" name="work description" required>
        <input type="number" placeholder="number of specialists" name="numberOfSpecialists" required>
        <select name="qualification">
            <jsp:useBean id="qualifications" scope="request" type="java.util.List"/>
            <c:forEach var="qualification" items= "${qualifications}">
                <option value="${qualification}">${qualification.value}</option>
            </c:forEach>
        </select>
        <br><br>
        <button type="submit">Next</button>
    </div>
</form>
</body>
</html>
