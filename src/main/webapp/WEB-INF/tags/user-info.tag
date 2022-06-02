<%@ attribute name="user" required="true" rtexprvalue="true" type="com.epam.amazingServlet.domain.user.User"%>
<jsp:useBean id="user" scope="request" type="com.epam.amazingServlet.domain.user.User"/>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<div>
    <c:out value="${user.firstName}" /><br>
    <c:out value="${user.lastName}" /><br>
    <c:out value="${user.email}" /><br>
    <c:out value="${user.position}" />
</div>
</html>
