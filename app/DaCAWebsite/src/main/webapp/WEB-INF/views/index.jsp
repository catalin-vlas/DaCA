<%--
  Created by IntelliJ IDEA.
  User: virgil
  Date: 09.12.2016
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="myApp">
<head>
    <meta charset="utf-8" />
    <title>DaCA</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

    <!-- Bootstrap-select -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

    <!-- Custom -->
    <link rel="stylesheet" href="<c:url value='/static/css/app.css' />">
</head>
<body>
    <!-- The navbar -->
    <!-- Not needed for DaCA -->
    <!-- <div ng-include src="'static/partials/navbar.component.html'"></div> -->

    <div class="container">
        <div class="row" ng-class="{ 'alert': flash, 'alert-success': flash.type === 'success', 'alert-danger': flash.type === 'error' }" ng-if="flash" ng-bind="flash.message"></div>
        <div class="row" ng-view></div>
    </div>

    <!-- The footer of the page -->
    <div ng-include src="'static/partials/footer.component.html'"></div>

    <!-- Javascript -->
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>

    <!-- Angular -->
    <script src="//code.angularjs.org/1.2.20/angular.js"></script>
    <script src="//code.angularjs.org/1.2.20/angular-route.js"></script>
    <script src="//code.angularjs.org/1.2.13/angular-cookies.js"></script>

    <!-- Bootstrap -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- livestamp (https://mattbradley.github.io/livestampjs/) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.17.1/moment.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/livestamp/1.1.2/livestamp.js" type="text/javascript"></script>

    <!-- Bootstrap-select -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <!-- Controllers and services -->
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/service/authentication.service.js' />"></script>
    <script src="<c:url value='/static/js/service/flash.service.js' />"></script>
    <script src="<c:url value='/static/js/service/user_service.js' />"></script>
    <script src="<c:url value='/static/js/service/friends_service.js' />"></script>
    <script src="<c:url value='/static/js/service/events_service.js' />"></script>

    <script src="<c:url value='/static/js/controller/home.controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/login.controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/register.controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/events.controller.js' />"></script>
</body>
</html>
