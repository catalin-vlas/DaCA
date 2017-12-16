<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS $http Example</title>

    <!-- CSS imports -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>

<body ng-app="myApp" ng-strict-di class="ng-cloak">
    <div class="generic-container" ng-controller="UserController as ctrl">

        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Balance</th>
                            <th width="20%"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="u in ctrl.users">
                            <td><span ng-bind="u.username"></span></td>
                            <td><span ng-bind="u.firstName"></span></td>
                            <td><span ng-bind="u.lastName"></span></td>
                            <td><span ng-bind="u.balance"></span></td>
                            <td>
                                <button type="button" ng-click="ctrl.edit(u.username)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.username)" class="btn btn-danger custom-width">Remove</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Added scripts here to improve the page loading time -->
    <script src="//code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="//code.angularjs.org/1.2.20/angular.js"></script>
    <script src="//code.angularjs.org/1.2.20/angular-route.js"></script>
    <script src="//code.angularjs.org/1.2.13/angular-cookies.js"></script>
    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/service/user_service.js' />"></script>
    <script src="<c:url value='/static/js/service/friends_service.js' />"></script>
    <script src="<c:url value='/static/js/controller/user.controller.js' />"></script>
</body>
</html>