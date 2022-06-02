<%--
  Created by IntelliJ IDEA.
  User: Demiurge
  Date: 31.05.2022
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="project" scope="request" type="com.epam.amazingServlet.domain.project.Project"/>
<jsp:useBean id="freeDevelopers" scope="request" type="java.util.List"/>
<jsp:useBean id="works" scope="request" type="java.util.List"/>
<jsp:useBean id="involvedDevelopers" scope="request" type="java.util.List"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>project</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <label class="custom-control-label" for="required-developers"><p class="mb-4 mt-5">Works in project <strong>"${project.projectName}"</strong></p>
        </label>

        <div id="required-developers">
            <c:forEach var="work" items="${works}">
                <div class="card text-center" style="width: 25rem;">
                    <div class="card-body">
                        <p class="card-text">${work.description}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">qualification needed -
                            <mark>${work.requiredQualification}</mark>
                        </li>
                        <li class="list-group-item">number of specialists <span
                                class="badge bg-primary rounded-pill">${work.numberOfRequiredSpecialists}  </span></li>
                    </ul>
                </div>
            </c:forEach>
        </div>

        <label class="custom-control-label" for="devsCheck"><p class="mt-5 ">Select developers to project
            <strong>"${project.projectName}"</strong></p></label>

        <form action="projectList" id="devsCheck" method="post" class="border border-light">
            <c:forEach var="developer" items="${freeDevelopers}">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="developerId" value="${developer.id}"
                           id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        <p><em>${developer.firstName} ${developer.lastName}
                            <mark>${developer.qualification.value}</mark>
                        </em></p>
                    </label>
                </div>
            </c:forEach>
            <input type="hidden" name="projectId" value="${project.id}">
            <p class="mt-5 ">involved users:
                <strong>${involvedDevelopers.size()}</strong>
            </p>
            <button type="submit" class="btn btn-info btn-block my-4">Submit</button>

        </form>

    </div>
</div>
</body>
</html>
