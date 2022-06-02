<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.05.2022
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <fmt:requestEncoding value="UTF-8" />
    <fmt:setBundle basename = "resource" var = "mess"/>
    <fmt:message key="greeting" bundle = "${mess}"/>
    <!-- CSS only -->

    <title>Title</title>
</head>
<body>

<div class="row justify-content-center">
    <div class="col-xs-9 col-sm-8 col-md-7 col-lg-6 col-xl-5 col-xxl-4">
        <form action="auth" method="post" class="border border-light p-5 row justify-content-center">

            <p class="h4 mb-4 text-center">Sign in</p>

            <input type="email" name="email" required id="defaultLoginFormEmail" class="form-control mb-4" placeholder="E-mail">

            <input type="password" name="psw" required id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password">

            <div class="d-flex justify-content-between">
                <div>
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                        <label class="custom-control-label" for="defaultLoginFormRemember">Remember me</label>
                    </div>
                </div>
                <div>
                    <a href="register">Don't have an account?</a>
                </div>
            </div>

            <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

        </form>
    </div></div>



</body>
</html>
