<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page isELIgnored="false" %>
<jsp:useBean id="developers" scope="request" type="java.util.List"/>
<html>
<body>



hello
<br/>
<br/>



<c:forEach var="developer" items="${developers}">
 ${developer.firstName} ${developer.lastName}
 <br/>
</c:forEach>
</body>
</html>
