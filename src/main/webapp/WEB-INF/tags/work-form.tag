<%@ tag import="com.epam.amazingServlet.domain.user.Qualification" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.05.2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ attribute name="work" required="true" rtexprvalue="true" type="com.epam.amazingServlet.domain.project.Work"%>

<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="qualifications" value="<%=Qualification.values()%>"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename = "resource" var = "mess"/>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty work}">
    <div class="mb-3">
        <label for="description" class="form-label"><fmt:message key="Work_description" bundle = "${mess}"/></label>
        <textarea name="workDescription" class="form-control" required id="description" rows="3"></textarea>

        <label for="numberOfSpecialists" class="form-label"><fmt:message key="Num_of_requ_spec" bundle = "${mess}"/></label>
        <input type="number" name="numberOfRequiredSpecialists" required id="numberOfSpecialists" class="form-control mb-4" placeholder="0">

        <label for="qualification" class="form-label"><fmt:message key="req_qualific" bundle = "${mess}"/></label>
        <select id="qualification" name="requiredQualification" class="form-select" required aria-label="Default select example">
            <c:forEach var="qualification" items="${qualifications}">
                <option value="${qualification}">${qualification.value}</option>
            </c:forEach>
        </select>
    </div>
</c:if>
<c:if test="${not empty work}">
    <div class="mb-3">
        <label for="description1" class="form-label"><fmt:message key="Work_description" bundle = "${mess}"/></label>
        <textarea name="workDescription" class="form-control" required id="description1" rows="3">${work.description}</textarea>

        <label for="numberOfSpecialists1" class="form-label">fmt:message key="Num_of_requ_spec" bundle = "${mess}"/></label>
        <input value="${work.numberOfRequiredSpecialists}" type="number" name="numberOfRequiredSpecialists" required id="numberOfSpecialists1" class="form-control mb-4" placeholder="0">

        <label for="qualification1" class="form-label"><fmt:message key="req_qualific" bundle = "${mess}"/></label>
        <select id="qualification1" name="requiredQualification" class="form-select" required aria-label="Default select example">
            <c:forEach var="qualification" items="${qualifications}">
                <c:if test="${work.requiredQualification==qualification}">
                    <option selected value="${qualification}">${qualification.value}</option>
                </c:if>
                <c:if test="${work.requiredQualification!=qualification}">
                    <option value="${qualification}">${qualification.value}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
</c:if>
</body>
</html>
