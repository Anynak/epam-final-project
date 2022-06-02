<%--
  Created by IntelliJ IDEA.
  User: Demiurge
  Date: 18.05.2022
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>

<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>home</title>
</head>
<body>
 <H1>hello
 <c:out value="${user.firstName}" /></H1>
 <a href="editProfile">edit</a>
</body>
</html>
