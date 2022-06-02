<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>

<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>about user</title>
</head>
<body>
<u:user-info user="${user}"/><br>
</body>
</html>
