<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.06.2022
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="currentWorks" scope="request" type="java.util.List"/>



<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <c:forEach var="currentWork" items="${currentWorks}">
            <div class="card text-center" style="width: 25rem;">
                <div class="card-body">
                    <p class="card-text">${currentWork.description}</p>
                </div>
                <ul class="list-group list-group-flush">

                    <li class="list-group-item">add working hours
                        <form action="setBill" method="post" class="border border-light p-2">
                            <input type="number" name="hours" required id="numberOfSpecialists" class="form-control mb-4" placeholder="0">
                            <button class="btn btn-info btn-block my-4" type="submit">Add</button>
                        </form>

                    </li>
                </ul>
            </div>
        </c:forEach>
        <a href="editProfile">edit profile</a>
    </div>
</div>

</body>
</html>
